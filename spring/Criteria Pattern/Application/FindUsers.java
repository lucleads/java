@Service
@AllArgsConstructor
public class FindUsers {
    //JPA Repository
    private final UsersRepository usersRepository;

    public List<User> search(Integer id, String cif, String name) {

        User user = new User(id, name, cif);
        ExampleMatcher exampleMatcher = createExampleMatcher();
        Example<User> userExample = Example.of(user, exampleMatcher);

        return usersRepository.findAll(userExample);
    }

    private static ExampleMatcher createExampleMatcher() {

        return ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }
}