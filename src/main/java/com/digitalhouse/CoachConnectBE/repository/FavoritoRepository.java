package com.digitalhouse.CoachConnectBE.repository;

import com.digitalhouse.CoachConnectBE.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito,Long> {
    @Query("SELECT f FROM Favorito f WHERE f.estudiante.id = :estudianteId")
    List<Favorito> findByEstudianteId(@Param("estudianteId") Long estudianteId);

    @Transactional
    @Modifying
    @Query("DELETE from Favorito f WHERE f.estudiante.id = :estudianteId AND f.tutoria.id = :tutoriaId")
    void deleteByEstudianteIdAndTutoriaId(@Param("estudianteId") Long estudianteId, @Param("tutoriaId") Long tutoriaId);
}
