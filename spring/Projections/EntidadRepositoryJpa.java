package com.app.content.entidad.infrastructure.repository.jpa;
import com.app.content.rem.infrastructure.repository.projections.EntidadIdAndNombre;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadRepositoryJpa extends JpaRepository<Entidad, Integer> {
   List<EntidadIdAndNombre> findAllBy();
}
