package com.challenge.literatura.dominio.repositorio;

import com.challenge.literatura.dominio.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByLengua(String lengua);

    Optional<Libro> findByNombreTituloIgnoreCase(String nombreTitulo);
}
