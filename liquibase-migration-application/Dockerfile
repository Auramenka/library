FROM maven:3.9.4-eclipse-temurin-17

WORKDIR /app

COPY . .

ENTRYPOINT ["sh", "-c", "mvn dependency:resolve && mvn clean package -DskipTests && mvn liquibase:update"]