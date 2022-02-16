@RestController
@AllArgsConstructor
@RequestMapping("/users")
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
