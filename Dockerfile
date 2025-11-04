# Usa una imagen base de OpenJDK con la versión de Java 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
# Asegúrate de que el nombre del archivo JAR coincida con el que se genera en tu proyecto Maven
COPY target/clients-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación Spring Boot (por defecto es 8081)
EXPOSE 8081

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
