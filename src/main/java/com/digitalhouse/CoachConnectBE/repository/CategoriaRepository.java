package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Categoria c " +
            "SET c.nombre = :nombre " +
            "WHERE c.id = :id")
    void update(
            @Param("id") Long id,
            @Param("nombre") String nombre
    );



    Optional<Categoria> findCategoriaById(Long id);
}
