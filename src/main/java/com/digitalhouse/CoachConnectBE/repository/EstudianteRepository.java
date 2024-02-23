package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Estudiante;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Estudiante est " +
            "SET est.nivelEducativo = :nivelEducativo " +
            "WHERE est.id = :id")
    void update(@Param("id") Long id, @Param("nivelEducativo") String nivelEducativo);



    Optional<Estudiante> findEstudianteById(Long id);
}
