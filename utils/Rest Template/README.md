# **Rest template**

`NOTE: Rest template quedará deprecated en próximas versiones. Utilizar WebClient para el desarrollo de nuevas aplicaciones.`

Esta clase es utilizada para realizar peticiones Web, y poder mapear su respuesta en un [POJO] (https://www.javatpoint.com/pojo-in-java). 
Aquí encontrarás un ejemplo de RestTemplate ya creado, utilizando el método:

```java
public static RestTemplate obtenerRestTemplate()
```



Y además, otro método que permite hacer la petición, haciendo uso de este primer RestTemplate creado:

```java
public static Object realizarPeticion(
    HttpMethod metodo, String rutaPeticion, String body, Class<?> claseRespuesta)
```

Para hacer uso de este segundo método, debemos introducir por parámetro el tipo de petición a realizar (GET, POST...), la ruta, el body de la petición en caso de tenerlo, y la clase en la que esperamos mapear la respuesta de la petición.

