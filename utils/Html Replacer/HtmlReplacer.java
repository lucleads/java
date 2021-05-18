package com.boarding.shared;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** @author Lucas Dante Elizalde on Dec, 2020 */
@Slf4j
public class HtmlReplacer {
  /**
   * Método que recibe la plantilla html como string y una entidad. Busca en el código HTML un
   * comentario con el formato "ENTIDAD.campoAReemplazar". En caso de encontrarlos, reemplaza su
   * valor en el código por el valor del campo en la entidad.
   *
   * @return
   * @throws IllegalAccessException
   */
  public String reemplazarCamposHtml(String codigoHtml, Object entidad)
      throws IllegalAccessException {
    for (Field field : entidad.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      Object value = field.get(entidad);
      String[] splEntidad = entidad.getClass().getName().toUpperCase().split("\\.");
      if (value != null) {
        if (codigoHtml.contains(
            "<!--" + splEntidad[splEntidad.length - 1] + "." + field.getName() + "-->")) {
          try {
            codigoHtml =
                codigoHtml.replace(
                    "<!--" + splEntidad[splEntidad.length - 1] + "." + field.getName() + "-->",
                    reflection(entidad, field.getName()));
          } catch (NoSuchMethodException | InvocationTargetException ex) {
            log.error(ex.toString());
          }
        }
      }
    }
    return codigoHtml;
  }

  /**
   * Método en caso de que la parte de código donde se encuentran los campos a reemplazar, se
   * encuentren en un comentario de un contexto superior. Esto puede suceder por ejemplo cuando la
   * parte de la plantilla solamente se muestra bajo determinadas condiciones.
   *
   * @return
   * @throws IllegalAccessException
   */
  public String reemplazarCamposHtmlEnComentario(String codigoHtml, Object entidad)
      throws IllegalAccessException {
    for (Field field : entidad.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      Object value = field.get(entidad);
      String[] splEntidad = entidad.getClass().getName().toUpperCase().split("\\.");
      if (value != null) {
        if (codigoHtml.contains(splEntidad[splEntidad.length - 1] + "." + field.getName())) {
          try {
            codigoHtml =
                codigoHtml.replace(
                    splEntidad[splEntidad.length - 1] + "." + field.getName(),
                    reflection(entidad, field.getName()));
          } catch (NoSuchMethodException | InvocationTargetException ex) {
            log.error(ex.toString());
          }
        }
      }
    }
    return codigoHtml;
  }

  /**
   * Método que recibe la plantilla html como string y una entidad. Busca en el código HTML un
   * comentario con el formato "ENTIDAD.campoAReemplazar". En caso de encontrarlos, reemplaza su
   * valor en el código por el valor del campo en la entidad. A diferencia del método anterior, este
   * identifica además campos diferenciados para diferentes instancias de una misma entidad,
   * añadiendo al final del valor a reemplazar el índice de su instancia. Para poder reemplazar el
   * valor del índice en el código html, se identifica añadiente el valor que contenga el parámetro
   * "regexCampo".
   *
   * @return
   * @throws IllegalAccessException
   */
  public String reemplazarCamposHtmlMultiple(
      String codigoHtml, Object entidad, String regexCampo, Integer indice)
      throws IllegalAccessException {
    for (Field field : entidad.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      Object value = field.get(entidad);
      String[] splEntidad = entidad.getClass().getName().toUpperCase().split("\\.");
      if (value != null) {
        if (codigoHtml.contains(
            "<!--" + splEntidad[splEntidad.length - 1] + "." + field.getName() + "-->")) {
          try {
            codigoHtml =
                codigoHtml.replace(
                    "<!--"
                        + splEntidad[splEntidad.length - 1]
                        + "."
                        + field.getName()
                        + "-->"
                        + "<!--NUM_"
                        + indice
                        + "-->",
                    reflection(entidad, field.getName()));
          } catch (NoSuchMethodException | InvocationTargetException ex) {
            log.error(ex.toString());
          }
        }
      }
    }
    return codigoHtml;
  }

  /**
   * Método para obtener el valor de un campo de una entidad, a partir del nombre de la entidad y el
   * nombre del campo. Este método hace uso de "Reflections".
   *
   * @param entidad
   * @param fieldName
   * @return
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  private String reflection(Object entidad, String fieldName)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method =
        entidad
            .getClass()
            .getDeclaredMethod(
                "get" + (fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)));
    return method.invoke(entidad).toString();
  }
}
