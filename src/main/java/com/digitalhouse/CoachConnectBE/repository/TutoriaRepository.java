package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria,Long> {
    Optional<Tutoria> findTutoriaById(Long id);
}
