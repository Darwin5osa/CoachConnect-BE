package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NivelRepository extends JpaRepository<Nivel,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Nivel n " +
            "SET n.nombre = :nombre " +
            "WHERE n.id = :id")
    Integer update(
            @Param("id") Long id,
            @Param("nombre") String nombre
    );



    Optional<Nivel> findNivelById(Long id);
}
