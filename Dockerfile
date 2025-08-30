# Imagen base con Java 17 (compatible con Gradle y el server)
FROM openjdk:17-jdk-slim

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el contenido del repositorio al contenedor
COPY . .

# Damos permisos de ejecución al gradlew
RUN chmod +x ./gradlew

# Compilamos el proyecto con Gradle
RUN ./gradlew build

# Exponemos el puerto que usará el server
EXPOSE 8080

# Ejecutamos el .jar generado al arrancar el contenedor
CMD ["java", "-jar", "build/libs/PintoServer-0.1.0.jar"]
