FROM openjdk:21

WORKDIR /app

COPY target/library-application-system-0.0.1-SNAPSHOT.jar library-system.jar

ENTRYPOINT ["java", "-jar", "library-system.jar"]