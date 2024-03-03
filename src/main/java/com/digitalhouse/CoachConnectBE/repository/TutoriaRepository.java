package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria,Long> {
    Optional<Tutoria> findTutoriaById(Long id);
}
