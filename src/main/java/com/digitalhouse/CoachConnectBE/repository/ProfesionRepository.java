package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Profesion p " +
            "SET p.nombre = :nombre " +
            "WHERE p.id = :id")
    Integer update(
            @Param("id") Long id,
            @Param("nombre") String nombre
    );



    Optional<Profesion> findProfesionById(Long id);
}
