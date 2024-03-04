package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Tutor t SET t.profesion = (SELECT p FROM Profesion p WHERE p.id = :profesionId), t.descripcion = :descripcion WHERE t.id = :id")
    Integer update(
            @Param("id") Long id,
            @Param("profesionId") Long profesionId,
            @Param("descripcion") String descripcion
    );

    Optional<Tutor> findTutorById(Long id);
}
