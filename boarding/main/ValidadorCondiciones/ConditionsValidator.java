package com.haya.boarding.shared;

import com.haya.boarding.content.condicion.domain.Condicion;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/** @author Lucas Dante Elizalde on Dic, 2020 */
public class ConditionsValidator {
  public Boolean comprobarCondicion(EntidadPrincipal entidadPrincipal, Condicion condicion)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          ScriptException {
    Object entidad = obtenerEntidadDesdeEntidadPrincipal(entidadPrincipal, condicion.getCapasDesdeEntidadPrincipal());
    if (Objects.isNull(entidad)
        || (entidad instanceof Collection) && ((Collection<?>) entidad).isEmpty()) {
      return false;
    }

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");

    Boolean cumple = true;

    if (entidad instanceof Collection<?>) {
      for (Object o : (Collection) entidad) {
        if (Objects.nonNull(o)) {
          String valorCondicion = reflection(o, condicion.getCampo());

          StringJoiner strCondicion = new StringJoiner("");
          strCondicion.add(obtenerValor(valorCondicion));
          strCondicion.add(obtenerOperador(condicion.getOperador()));
          strCondicion.add("\"" + condicion.getValor() + "\"");
          cumple = (Boolean) engine.eval(strCondicion.toString());
          if (!cumple) break;
        } else {
          cumple = false;
        }
      }
    } else {
      String valorCondicion = reflection(entidad, condicion.getCampo());

      StringJoiner strCondicion = new StringJoiner(" ");
      strCondicion.add(obtenerValor(valorCondicion));
      strCondicion.add(obtenerOperador(condicion.getOperador()));
      strCondicion.add(condicion.getValor());
      cumple = (Boolean) engine.eval(strCondicion.toString());
    }
    return cumple;
  }

  /**
   * Obtiene un objeto que hereda de la entidad principal introducida por parámetros, y se encuentra en la
   * última capa de una lista introducida como String, que contiene todas las capas separadas por comas.
   *
   * @param ent - Entidad principal
   * @param capasDesdeEntidadPrincipal - Capas desde la entidad principal al objeto requerido
   * @return Objeto requerido
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  private Object obtenerEntidadDesdeEntidadPrincipal(Object ent, String capasDesdeEntidadPrincipal)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    String[] capas = Objects.isNull(capasDesdeEntidadPrincipal) ? null : capasDesdeEntidadPrincipal.split(",");
    String[] capaArr = capas;
    List<Object> result = new ArrayList<>();

    if (Objects.isNull(capas)) {
      return ent;
    }

    Method method = ent.getClass().getDeclaredMethod("get" + capas[0]);

    if (capas.length != 1) {
      Object sigCapa = method.invoke(ent);
      capaArr = ArrayUtils.remove(capaArr, 0);
      if (sigCapa instanceof Collection<?>) {
        for (Object o : (Collection) sigCapa) {
          Object res = obtenerEntidadDesdeEntidadPrincipal(o, String.join(",", capaArr));
          if (res instanceof Collection<?>) {
            result.addAll(((Collection<?>) res));
          } else {
            result.add(res);
          }
        }
      } else {
        Object res = obtenerEntidadDesdeEntidadPrincipal(sigCapa, String.join(",", capaArr));
        if (res instanceof Collection<?>) {
          result.addAll(((Collection<?>) res));
        } else {
          result.add(res);
        }
      }
    } else {
      Object r = method.invoke(ent);
      if (Objects.isNull(r)) {
        throw new IllegalAccessException(
            "La llamada al método "
                + ent.getClass().getName()
                + method.getName()
                + " devuelve NULL");
      }
      result.add(r);
    }

    return result;
  }

  private String obtenerValor(String valor) {
    return "\"" + valor + "\"";
  }

  private String obtenerOperador(String operador) {
    return operador;
  }

  /**
   * Método para obtener el valor de un campo de una entidad, a partir del nombre de la entidad y el
   * nombre del campo. Este método hace uso de "Reflections".
   *
   * @param entidad - Entidad en la que se encuentra el campo del que queremos obtener el valor
   * @param fieldName - Nombre del campo
   * @return Valor del campo en la entidad
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  private String reflection(Object entidad, String fieldName)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method;
    try {
      method = entidad.getClass().getDeclaredMethod("get" + fieldName);
    } catch (NoSuchMethodException ns) {
      method = entidad.getClass().getSuperclass().getDeclaredMethod("get" + fieldName);
    }
    return Objects.nonNull(method.invoke(entidad)) ? method.invoke(entidad).toString() : null;
  }
}
