# Usa una imagen de OpenJDK como base
FROM 767398085251.dkr.ecr.us-east-1.amazonaws.com/maven:3.9.6-amazoncorretto-17 AS build

# Variables de entorno
ENV RDS_ENDPOINT=${RDS_ENDPOINT}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /

# Copia el archivo POM y el resto del código fuente a la imagen del contenedor
COPY pom.xml .
COPY src ./src

# Empaqueta la aplicación utilizando Maven
RUN mvn clean package -DskipTests

# Crea una imagen del contenedor final
FROM 767398085251.dkr.ecr.us-east-1.amazonaws.com/amazoncorretto:17-alpine

# Copia el archivo JAR de la aplicación a la imagen del contenedor
COPY --from=build /target/CoachConnect-BE-0.0.1-SNAPSHOT.war /application.war


# Exponer el puerto en el que la aplicación se ejecuta dentro del contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "application.war"]
