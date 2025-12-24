package com.challenge.literatura.dominio.repositorio;

import com.challenge.literatura.dominio.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreCompleto(String nombreCompleto);
}
