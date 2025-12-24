package com.challenge.literatura.dominio.modelo;
import jakarta.persistence.*;

@Entity

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libroId;

    @Column(nullable = false)
    private String nombreTitulo;

    @Column(length = 10)
    private String lengua;

    @Column(name = "total_descargas")
    private Integer totalDescargas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Long getLibroId() {
        return libroId;
    }

    public String getNombreTitulo() {
        return nombreTitulo;
    }

    public void setNombreTitulo(String nombreTitulo) {
        this.nombreTitulo = nombreTitulo;
    }

    public String getLengua() {
        return lengua;
    }

    public void setLengua(String lengua) {
        this.lengua = lengua;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro: " + nombreTitulo +
                " | Idioma: " + lengua +
                " | Descargas: " + (totalDescargas != null ? totalDescargas : 0) +
                " | Autor: " + (autor != null ? autor.getNombreCompleto() : "N/A");
    }
}
