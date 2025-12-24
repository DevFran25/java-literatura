package com.challenge.literatura.servicio;

public enum OpcionMenu {

    BUSCAR_LIBRO(1, "Buscar libro por título"),
    LISTAR_LIBROS(2, "Mostrar todos los libros"),
    LISTAR_POR_IDIOMA(3, "Buscar libros por idioma"),
    LISTAR_AUTORES(4, "Mostrar todos los autores"),
    AUTORES_VIVOS(5, "Autores vivos en un año específico"),
    SALIR(6, "Salir");

    private final int codigo;
    private final String descripcion;

    OpcionMenu(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionMenu fromCodigo(int codigo) {
        for (OpcionMenu opcion : values()) {
            if (opcion.codigo == codigo) {
                return opcion;
            }
        }
        return null;
    }
}
