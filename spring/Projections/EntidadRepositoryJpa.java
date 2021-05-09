package com.haya.boarding.content.entidad.infrastructure.repository.jpa;
import com.haya.boarding.content.rem.infrastructure.repository.projections.EntidadIdAndNombre;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadRepositoryJpa extends JpaRepository<Entidad, Integer> {
   List<EntidadIdAndNombre> findAllBy();
}
