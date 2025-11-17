# ==========================
# 1. Build Stage (Maven + JDK 21)
# ==========================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml & download dependencies first (cache)
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Build
RUN mvn -B clean package -DskipTests


# ==========================
# 2. Run Stage (Java 25)
# ==========================
FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
