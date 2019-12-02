FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app
 
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
RUN chmod +x gradlew
RUN ./gradlew dependencies
 
FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","se.hig.exte.ExteBackendApplication"]