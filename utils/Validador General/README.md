# **Validador**


**NOTA:** 
`Algunas de las validaciones que realiza esta clase, se pueden reemplazar haciendo uso de la librería` [`javax.validations`](https://www.baeldung.com/javax-validation) `aplicadas en el propio dto de entrada`

Esta clase consta de distintos métodos para validar el formato introducido por parámetro, haciendo uso de expresiones regulares:

- Validar formato de CIF:

```java
public static boolean validarCif(String cif)
```

- Validar formato de E-mail:

```java
public static boolean validarEmail(String email)
```

- Validar formato de número de teléfono:

```java
public static boolean validarTlfn(String tlfn
```

- Validar formato de DNI:

```java
public static boolean validarDni(String dni)
```

- Validar formato de NIE:

```java
public static boolean validarNie(String nie)
```

- Validar formato de número de pasaporte:

```java
public static boolean validarPasaporte(String pasaporte)
```

