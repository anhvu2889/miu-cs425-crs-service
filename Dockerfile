FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/vehicle-rental-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

#FROM maven:3.8.3-openjdk-17 AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/home/app/target/vehicle-rental-system-0.0.1-SNAPSHOT.jar"]