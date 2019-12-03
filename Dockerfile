FROM openjdk:8 AS TEMP_BUILD_IMAGE
RUN mkdir -p /workspace
WORKDIR /workspace
COPY build.gradle settings.gradle gradlew /workspace
COPY gradle workspace/gradle
RUN chmod +x ./gradlew
RUN ./gradlew build || return 0 
RUN chmod +x ./gradlew
RUN ./gradlew build

FROM openjdk:8

RUN mkdir -p /app
COPY --from=TEMP_BUILD_IMAGE /workspace/build/libs/exte-backend-restapi.jar /app/exte-backend-restapi.jar

CMD ["java", "-jar", "/app/exte-backend-restapi.jar"]