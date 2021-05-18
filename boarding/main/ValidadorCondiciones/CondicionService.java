package com.boarding.content.condicion.application;

import com.boarding.content.clausula.domain.Clausula;
import com.boarding.content.clausula.domain.enums.TipoClausulaLogicaEnum;
import com.boarding.content.clausula_bloque_version.domain.ClausulaBloqueVersion;
import com.boarding.content.condicion.domain.Condicion;
import com.boarding.content.oferta.domain.EntidadPrincipal;
import com.boarding.exception.HashDontMatchException;
import com.boarding.exception.UnfulfilledConditionException;
import com.boarding.shared.ConditionsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;

@Service
@AllArgsConstructor
public class CondicionServiceImpl implements CondicionService {
  private final ConditionsValidator conditionsValidator;

  @Override
  public boolean validarCondicionClausula(EntidadPrincipal entidadPrincipal, Clausula clausula, Condicion condicion)
      throws InvocationTargetException, NoSuchMethodException, ScriptException,
          IllegalAccessException, UnfulfilledConditionException {
    Boolean condicionOK = conditionsValidator.comprobarCondicion(entidadPrincipal, condicion);

    // Comprobar condición es Excluyente
    if (!condicionOK
        && clausula.getTipoClausulaLogica().equals(TipoClausulaLogicaEnum.EXCLUYENTE)) {
      throw new UnfulfilledConditionException(
          "El Contrato no se ha podido generar: " + condicion.getDescripcion());
    }

    return condicionOK;
  }

  @Override
  public boolean validarCondicionVersion(
      EntidadPrincipal entidadPrincipal, ClausulaBloqueVersion clausulaBloqueVersion, Condicion condicion)
      throws ScriptException, InvocationTargetException, NoSuchMethodException,
          IllegalAccessException {

    return conditionsValidator.comprobarCondicion(entidadPrincipal, condicion);
  }
}
