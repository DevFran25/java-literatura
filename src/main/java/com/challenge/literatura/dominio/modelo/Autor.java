package com.challenge.literatura.dominio.modelo;
import jakarta.persistence.*;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(name = "anio_nacimiento")
    private Integer anioNacimiento;

    @Column(name = "anio_fallecimiento")
    private Integer anioFallecimiento;

    public Long getAutorId() {
        return autorId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    @Override
    public String toString() {
        return "Autor: " + nombreCompleto +
                " | Nacimiento: " + (anioNacimiento != null ? anioNacimiento : "Desconocido") +
                " | Fallecimiento: " + (anioFallecimiento != null ? anioFallecimiento : "Desconocido");
    }
}

