package com.haya.boarding.shared;

import com.haya.boarding.content.config_variable.application.ConfiguracionVariableService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/** @author Lucas Dante Elizalde on Oct, 2020 */
@Component
@NoArgsConstructor
public class ConfigVar {
  private static HashMap mapaVariables = new HashMap<String, String>();
  @Autowired ConfiguracionVariableService configuracionVariableService;

  public static String get(String key) {
    return (String) mapaVariables.get(key);
  }

  @PostConstruct
  public void init() {
    configuracionVariableService.getMap().stream()
        .forEach(objects -> mapaVariables.put(objects[0], objects[1]));
  }
}
