package com.digitalhouse.CoachConnectBE.repository;

import com.digitalhouse.CoachConnectBE.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
