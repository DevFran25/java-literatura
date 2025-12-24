# 📚 Literatura - Aplicación Spring Boot

Aplicación desarrollada en **Java con Spring Boot** que permite consultar libros desde la API pública **Gutendex**, almacenar la información en una base de datos **PostgreSQL** y realizar distintas consultas mediante un menú por consola.

El proyecto aplica conceptos de:
- Arquitectura en capas
- Consumo de APIs REST externas
- Persistencia con JPA / Hibernate
- Relaciones entre entidades (Libro - Autor)

---

## 🎯 Objetivo del Proyecto

Construir una aplicación backend que:
- Consuma datos desde una API externa
- Persista información relevante en una base de datos
- Permita búsquedas y filtros desde consola
- Mantenga una estructura clara y ordenada

---

## 🚀 Funcionalidades

La aplicación ofrece un menú interactivo con las siguientes opciones:

1. Buscar libro por título (API Gutendex)
2. Guardar libros y autores en la base de datos
3. Mostrar todos los libros registrados
4. Buscar libros por idioma
5. Mostrar todos los autores
6. Listar autores vivos en un año específico
7. Salir del programa

---

## 🧩 Arquitectura del Proyecto

Estructura de paquetes:

com.challenge.literatura  
│  
├── cliente  
│   └── GuntendexCliente.java  
│  
├── dominio  
│   ├── modelo  
│   │   ├── Libro.java  
│   │   └── Autor.java  
│   └── repositorio  
│       ├── LibroRepository.java  
│       └── AutorRepository.java  
│  
├── servicio  
│   └── BibliotecaService.java  
│  
└── LiteraturaApplication.java  

---

## 🛠️ Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jackson
- API Gutendex

---

## ⚙️ Configuración

Archivo `application.properties`:

spring.application.name=literatura  
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_literatura  
spring.datasource.username=postgres  
spring.datasource.password=postgres  

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=false  
spring.jpa.properties.hibernate.format_sql=true  
spring.jpa.open-in-view=false  

---

## ▶️ Ejecución del Proyecto

1. Crear la base de datos en PostgreSQL:

CREATE DATABASE bd_literatura;

2. Abrir el proyecto en el IDE
3. Ejecutar la clase principal `LiteraturaApplication`
4. Usar el menú desde la consola

---

## 🌐 API Externa

La aplicación consume datos desde:

https://gutendex.com/books/

API pública del Proyecto Gutenberg.

---

## 🧪 Pruebas

Las pruebas se realizan desde la consola:
- Búsqueda de libros por título
- Verificación de duplicados
- Consulta de libros y autores
- Filtro por idioma
- Autores vivos en un año determinado

---

## 👤 Autor

Proyecto desarrollado como práctica de Spring Boot, JPA y consumo de APIs REST.
