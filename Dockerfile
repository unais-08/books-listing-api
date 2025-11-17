# ==========================
# 1. Build Stage (Maven + JDK 25)
# ==========================
FROM maven:3.9.6-eclipse-temurin-25 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn -B clean package -DskipTests


# ==========================
# 2. Run Stage (JDK 25)
# ==========================
FROM eclipse-temurin:25-jdk

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Render injects PORT dynamically → required
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
