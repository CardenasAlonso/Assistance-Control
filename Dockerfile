# Etapa 1: Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el .jar generado
COPY --from=build /app/target/*.jar app.jar

# ¡AQUÍ ESTÁ LA MAGIA! Copiamos la carpeta del Wallet al contenedor final
COPY Wallet_SchoolManagment ./Wallet_SchoolManagment

# Exponemos el puerto
EXPOSE 8085
# Comando para iniciar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]