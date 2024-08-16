# Prueba técnica para BSide - Spring Boot y Java 21

## Descripción del Proyecto

Se creó una aplicación para la gestión de alumnos en la que los usuarios pueden crear, listar, actualizar y eliminar alumnos. La aplicación fue desarrollada utilizando Spring Boot y Java 21, aprovechando algunas características de Java 21.

## Requisitos

1. **Java 21**: Se utilizaron algunas de las nuevas características de Java 21, como mejoras en el Stream API
2. **Spring Boot**: Se creó una API REST utilizando Spring Boot.
3. **Persistencia con JPA**: Se empleó JPA para la gestión de las entidades y PostgreSQL como base de datos.
4. **Gestión de Errores**: Se implementó una gestión de errores con excepciones personalizadas y manejo global de errores.
5. **Validación**: Se validaron los datos de entrada utilizando anotaciones de validación de Spring.
6. **Pruebas Unitarias**: Se escribieron pruebas unitarias para algunos controladores y servicios utilizando JUnit y Mockito.
7. **Contenerización**: Se creó un archivo Docker Compose para la contenerización de la base de datos PostgreSQL.

## Funcionalidades del Proyecto

1. **CRUD de Alumnos**:
   - Se implementaron funcionalidades para crear, listar, actualizar y eliminar alumnos.
   - Los alumnos tienen atributos como nombre, apellidos, edad, etc.

2. **Persistencia con PostgreSQL**:
   - Se configuró la base de datos PostgreSQL para almacenar los alumnos.
   - Se utilizó JPA para la gestión de las entidades.

3. **Validación de Datos**:
   - Se validaron los datos de entrada en los endpoints (por ejemplo, que el nombre de los alumnos no esté vacío).
   - Se manejaron los errores de validación de manera adecuada y se devolvieron respuestas HTTP apropiadas.

4. **Gestión de Errores**:
   - Se implementaron excepciones personalizadas para manejar casos como la no existencia de un alumno al intentar actualizarlo o eliminarlo.
   - Se creó un controlador global de excepciones para capturar y manejar errores en toda la aplicación.

5. **Pruebas Unitarias**:
   - Se escribieron pruebas unitarias para algunos métodos de los controladores y de los servicios.
   - Se utilizó Mockito para simular el comportamiento del repositorio.
6. **Contenerización con Docker**:
   - Se creó un archivo Docker Compose (`compose.yml`) para la contenerización de la base de datos PostgreSQL, facilitando la configuración y el despliegue del entorno de base de datos.
   - Para ejecutar el archivo Docker Compose, se puede utilizar el siguiente comando:
     ```bash
     docker-compose -f compose.yml up
     ```