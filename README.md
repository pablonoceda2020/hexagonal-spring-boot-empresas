# hexagonal-spring-boot-enterprise


# Running the app locally

Set up a `PostgreSQL` installation and create a database using e.g. `PgAdmin 4`
according to the properties in the `application.yml`. Run
the application from
your IDE or via `mvn spring-boot:run`. Verify it works correctly without any warnings or errors in the terminal. Then,
shut down the application once again.

# Running the app from a docker container

First, compile the whole app via `mvn clean package -DskipTests`. Then, copy the compiled executable JAR file to
the `docker` directory with the following
command: `cp target/challenge-0.0.1-SNAPSHOT.jar src/main/docker`
Navigate into the `docker` folder via `cd src/main/docker`. Make
sure `docker` is installed and the daemon is started. Now run the
command `docker-compose up` to set up and execute your docker container based on the `docker-compose.yml`. Verify
that the app once again works as expected.

# Swagger
Run the project. open the below link in the browser.

http://localhost:8080/swagger-ui/index.html

# Contenidos del proyecto:

<ul>
<li>Spring Boot. </li>
<li>Test unitarios de todas las capas (Controller, Servicio, Negocio). </li>
<li>Tratamiento de excepciones. </li>
<li>Validaciones de datos. </li>
<li>Manejo de errores http.</li>
<li>Seguir principio RESTful. </li>
<li>Documentación Swagger/OpenAPI. </li>
<li>Logging: configuración y uso correcto de logs. </li>
<li>Arquitectura hexagonal. </li>
<li>Manejo de base de datos relacional. </li>
</ul>










