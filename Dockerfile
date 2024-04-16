# Usa una imagen base con Java y Gradle instalados
FROM gradle:6.4.1-jdk14 AS builder

# Copia los archivos de configuración y el código fuente al contenedor
COPY --chown=gradle:gradle . /home/gradle/src

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /home/gradle/src

# Compila el proyecto usando Gradle
RUN gradle cleanBuild --no-daemon

# Crea una imagen final con el artefacto compilado
FROM openjdk:11.0.16-jre-slim

# Copia el artefacto compilado desde el contenedor anterior
COPY --from=builder /home/gradle/src/build/libs/feedback-workshop-0.0.1-SNAPSHOT.jar /app/your-application.jar

EXPOSE 8080
# Define el comando por defecto a ejecutar cuando se inicie el contenedor
#CMD ["java", "-jar", "/app/your-application.jar"]
ENTRYPOINT [ "java", "-jar", "/app/your-application.jar" ]