# Usa una imagen de OpenJDK como base
FROM maven:3.8.3-amazoncorretto-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /

# Copia el archivo POM y el resto del código fuente a la imagen del contenedor
COPY pom.xml .
COPY src ./src

# Empaqueta la aplicación utilizando Maven
RUN mvn clean package

# Crea una imagen del contenedor final
FROM amazoncorretto:17-alpine

# Copia el archivo JAR de la aplicación a la imagen del contenedor
COPY --from=build /target/CoachConnect-BE-0.0.1-SNAPSHOT.war /application.war


# Exponer el puerto en el que la aplicación se ejecuta dentro del contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "application.war"]
