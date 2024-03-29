package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Caracteristica c " +
            "SET c.nombre = :nombre, " +
            "c.icono = :icono " +
            "WHERE c.id = :id")
    Integer update(
        @Param("id") Long id,
        @Param("nombre") String nombre,
        @Param("icono") String icono
    );

    Optional<Caracteristica> findCaracteristicaById(Long id);
}
