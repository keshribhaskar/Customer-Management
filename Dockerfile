FROM khipu/openjdk17-alpine:latest
WORKDIR /app
COPY target/custms-0.0.1-SNAPSHOT.jar customerMsApp.jar
CMD ["java", "-jar", "customerMsApp.jar"]
EXPOSE 8081