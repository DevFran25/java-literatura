package com.challenge.literatura.servicio;

import com.challenge.literatura.cliente.GuntendexCliente;
import com.challenge.literatura.dominio.modelo.Libro;
import com.challenge.literatura.dominio.modelo.Autor;
import com.challenge.literatura.dominio.repositorio.AutorRepository;
import com.challenge.literatura.dominio.repositorio.LibroRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BibliotecaService {

    private final GuntendexCliente gutendexClient = new GuntendexCliente();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public BibliotecaService(LibroRepository libroRepository,
                             AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    1. Buscar libro por título
                    2. Mostrar todos los libros
                    3. Buscar libros por idioma
                    4. Mostrar todos los autores
                    5. Autores vivos en un año específico
                    6. Salir
                    """);

            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el título: ");
                        buscarLibroPorTitulo(scanner.nextLine());
                    }
                    case 2 -> listarLibros();
                    case 3 -> {
                        System.out.print("Ingrese el idioma: ");
                        listarLibrosPorIdioma(scanner.nextLine());
                    }
                    case 4 -> listarAutores();
                    case 5 -> {
                        System.out.print("Ingrese el año: ");
                        if (scanner.hasNextInt()) {
                            listarAutoresVivosEn(scanner.nextInt());
                            scanner.nextLine();
                        } else {
                            System.out.println("Año inválido.");
                            scanner.nextLine();
                        }
                    }
                    case 6 -> {
                        System.out.println("Finalizando programa.");
                        return;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Transactional
    public void buscarLibroPorTitulo(String titulo) throws Exception {

        if (libroRepository.findByNombreTituloIgnoreCase(titulo).isPresent()) {
            System.out.println("El libro ya está registrado.");
            return;
        }

        String response = gutendexClient.buscarLibroPorTitulo(titulo);
        JsonNode root = objectMapper.readTree(response);

        if (root.get("results").isEmpty()) {
            System.out.println("No se encontraron resultados.");
            return;
        }

        JsonNode libroJson = root.get("results").get(0);
        JsonNode autorJson = libroJson.get("authors").get(0);

        String nombreAutor = autorJson.get("name").asText();

        Autor autor = autorRepository
                .findByNombreCompleto(nombreAutor)
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor();
                    nuevoAutor.setNombreCompleto(nombreAutor);
                    nuevoAutor.setAnioNacimiento(
                            autorJson.get("birth_year").isNull()
                                    ? null
                                    : autorJson.get("birth_year").asInt()
                    );
                    nuevoAutor.setAnioFallecimiento(
                            autorJson.get("death_year").isNull()
                                    ? null
                                    : autorJson.get("death_year").asInt()
                    );
                    return autorRepository.save(nuevoAutor);
                });

        Libro libro = new Libro();
        libro.setNombreTitulo(libroJson.get("title").asText());
        libro.setLengua(libroJson.get("languages").get(0).asText());
        libro.setTotalDescargas(libroJson.get("download_count").asInt());
        libro.setAutor(autor);

        libroRepository.save(libro);

        System.out.println("Libro guardado correctamente: " + libro.getNombreTitulo());
    }

    private void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        libros.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByLengua(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
            return;
        }
        libros.forEach(System.out::println);
    }

    private void listarAutores() {
        autorRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresVivosEn(int anio) {
        autorRepository.findAll().stream()
                .filter(a ->
                        (a.getAnioNacimiento() == null || a.getAnioNacimiento() <= anio) &&
                                (a.getAnioFallecimiento() == null || a.getAnioFallecimiento() >= anio)
                )
                .forEach(System.out::println);
    }
}
