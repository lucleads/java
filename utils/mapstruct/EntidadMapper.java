import com.boarding.content.expediente_boarding.domain.Entidad;
import com.boarding.content.expediente_boarding.infrastructure.controller.dto.output.EntidadOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/** @author Lucas Dante Elizalde on Dec, 2020 */
@Mapper
public interface EntidadMapper {
  EntidadMapper INSTANCE = Mappers.getMapper(EntidadMapper.class);

  @Mapping(target = "campo", source = "campo1.campoFinal") // Escalable a cualquier atributo
  @Mapping(
      target = "campo", expression = "java(entidad.metodoEntidad())") // Cualquier método de la entidad
  EntidadOutputDto toDto(Entidad entidad); // Objetos de los que parten los atributos
}
