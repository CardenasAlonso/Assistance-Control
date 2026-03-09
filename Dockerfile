# Etapa 1: Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el .jar generado en la etapa 1
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto de tu aplicación (asumiendo que es el 8085 según el doc)
EXPOSE 8085
# Comando para iniciar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]