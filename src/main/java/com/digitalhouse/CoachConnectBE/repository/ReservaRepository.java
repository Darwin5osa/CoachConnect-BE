package com.digitalhouse.CoachConnectBE.repository;

import com.digitalhouse.CoachConnectBE.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    @Query("SELECT r FROM Reserva r WHERE r.estudiante.id = :estudianteId")
    List<Reserva> findByEstudianteId(@Param("estudianteId") Long estudianteId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Reserva r " +
            "SET r.fechaInicio = :fechaInicio, r.fechaFin = :fechaFin, r.horasReservadas = :horasReservadas " +
            "WHERE r.id = :id")
    Integer update(
            @Param("id") Long id,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("horasReservadas") int horasReservadas
    );

    Optional<Reserva> findReservaById(Long id);
}
