/** @author Lucas Dante Elizalde on Dec, 2020 */
@RestController
@AllArgsConstructor
@RequestMapping("api/v0/entidades")
public class EntidadController {
  private final EntidadService service;
  
  @GetMapping
  public EntidadOutputDto buscarEntidad(@PathVariable int idEntidad)
      throws NotFoundException {
    return EntidadMapper.INSTANCE.toDto(service.buscarEntidad(idEntidad));
  }
}
