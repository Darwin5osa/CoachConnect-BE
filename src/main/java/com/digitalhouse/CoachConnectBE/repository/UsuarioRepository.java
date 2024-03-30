package com.digitalhouse.CoachConnectBE.repository;


import com.digitalhouse.CoachConnectBE.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u " +
            "SET u.nombre = :nombre, u.apellido = :apellido, u.edad = :edad, u.email = :email, u.password = :password, u.contactoCelular = :contactoCelular " +
            "WHERE u.id = :id")
    Integer update(
            @Param("id") Long id,
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("edad") int edad,
            @Param("email") String email,
            @Param("password") String password,
            @Param("contactoCelular") String contactoCelular
    );



    Optional<Usuario> findUsuarioByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u " +
            "SET u.rol = :rol " +
            "WHERE u.username = :username")
    Integer cambiarRol(@Param("username") String username, @Param("rol") String rol);

    Optional<Usuario> findUsuarioByUsername(String username);
}
