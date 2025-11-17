#############################
# 1. Build Stage (JDK 21)
#############################
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn -B clean package -DskipTests

#############################
# 2. Run Stage (JDK 21)
#############################
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
