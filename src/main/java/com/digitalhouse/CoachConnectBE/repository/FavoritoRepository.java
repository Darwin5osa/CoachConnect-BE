package com.digitalhouse.CoachConnectBE.repository;

import com.digitalhouse.CoachConnectBE.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito,Long> {
    @Query("SELECT f FROM Favorito f WHERE f.estudiante.id = :estudianteId")
    List<Favorito> findByEstudianteId(@Param("estudianteId") Long estudianteId);
}
