FROM openjdk:8-jdk-alpine
RUN mkdir -p /app/
ADD build/libs/ExteBackendApplication-0.1.0.jar /app/ExteBackendApplication-0.1.0.jar
CMD ["java","-jar", "/app.jar"]
