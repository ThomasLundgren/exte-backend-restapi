FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 9000
CMD ["java","-jar", "/app.jar"]