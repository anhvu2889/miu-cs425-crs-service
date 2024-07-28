# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.2/maven-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#features.docker-compose)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Docker Compose support

This project contains a Docker Compose file named `docker-compose.yaml`.
In this file, the following services have been defined:

* mysql: [`mysql:latest`](https://hub.docker.com/_/mysql)

Please review the tags of the used images and set them to the same as you're running in production.

### How to setup the environment

* Install JDK 17
* Install Maven 3.9.8
* Install Docker 27.0.3
* Install MySQL
    * DB script: `schema.sql`

### How to launch the application

* Run `CarRentalSystemApplication.java` file in IntelliJ
* Or you also run application by these command line:
    * Run with `dev` profile: `mvn spring-boot:run -Pdev -Dspring-boot.run.profiles=dev`
    * Run with `docker-compose`
      profile: `mvn spring-boot:run -Pdocker-compose -Dspring-boot.run.profiles=docker-compose`

### Swagger UI

* http://localhost:8080/swagger-ui/index.html
