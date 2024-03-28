package com.digitalhouse.CoachConnectBE.repository;

import com.digitalhouse.CoachConnectBE.entity.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository  extends JpaRepository<Resena,Long> {
    @Query("SELECT r FROM Resena r WHERE r.estudiante.id = :estudianteId")
    List<Resena> findByEstudianteId(@Param("estudianteId") Long estudianteId);

    @Query("SELECT r FROM Resena r WHERE r.tutoria.id = :tutoriaId")
    List<Resena> findByTutoriaId(@Param("tutoriaId") Long tutoriaId);
}
