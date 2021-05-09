package com.haya.boarding.shared.util.peticiones.application;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class PeticionesUtil {
  private final static int CONNECT_TIMEOUT = 5000;
  private final static int READ_TIMEOUT = 5000;

  /**
   * Genera una entidad RestTemplate con la configuración necesaria para realizar peticiones HTTP
   *
   * @return La entidad configurada
   */
  public static RestTemplate obtenerRestTemplate() {
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
    requestFactory.setReadTimeout(READ_TIMEOUT);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(requestFactory);
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

    return restTemplate;
  }

  /**
   * Realiza una petición general y devuelve su respuesta
   *
   * @param metodo El método con el que realizar la petición
   * @param rutaPeticion La ruta donde realizar la petición
   * @param body En caso de que fuera necesario, el contenido de la petición
   * @param claseRespuesta La clase DTO a la que mapear la respuesta
   * @return La respuesta de la petición
   */
  public static Object realizarPeticion(
      HttpMethod metodo, String rutaPeticion, String body, Class<?> claseRespuesta) {
    RestTemplate restTemplate = PeticionesUtil.obtenerRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> request = new HttpEntity<>(body, headers);

    ResponseEntity<?> response =
        restTemplate.exchange(rutaPeticion, metodo, request, claseRespuesta);

    return response.getBody();
  }
}
