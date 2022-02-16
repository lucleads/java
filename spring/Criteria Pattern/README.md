# EJEMPLO DE COMO APLICAR EL PATRÓN CRITERIA EN SPRING

***
El ejemplo consta de un caso de uso con una petición GET a un controlador que recibe parámetros opcionales
correspondientes a los filtros.

En el ejemplo se busca todos los objetos del tipo `User` que coincidan con los filtros aplicados.

***

### CONTROLADOR

Recibe todos los filtros opcionales en una petición GET y los envia al caso de uso.

```java
public class GetUsersController {

    private final FindUsers findUsers;

    @GetMapping
    public List<GetUserOutputDto> search(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "cif", required = false) String cif,
            @RequestParam(name = "name", required = false) String name) {

        return findUsers.search(id, cif, name).stream().map(UserOutputDto::of).collect(Collectors.toList());
    }
}
  ```

***

### CASO DE USO

Recibe los filtros, y a partir de un objeto de tipo ExampleMatcher, construye el Example a buscar por el repositorio.
Este example será de tipo Example<User>. El repositorio recibe el Example y devuelve una lista de objetos del tipo User
que coincidan con los filtros.

```java

@Service
public class FindUsers {
    //JPA Repository
    private final UsersRepository usersRepository;

    public List<User> search(Integer id, String cif, String name) {

        User user = new User(id, name, cif);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> userExample = Example.of(user, exampleMatcher);

        return usersRepository.findAll(userExample);
    }
}
  ```