package com.shared;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "config")
public class Configuracion {
  public String variablePrimera;
  public String variableSegunda;
  public String variableTercera;
}
