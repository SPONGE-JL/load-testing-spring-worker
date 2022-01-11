FROM gradle:7.2.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon clean build

FROM amazoncorretto:11.0.13-alpine
ARG JVM_OPS
COPY --from=build /home/gradle/src/build/libs/*-SNAPSHOT.jar /app.jar

ENTRYPOINT ["sh", "-c"]
CMD ["java ${JVM_OPS} -jar app.jar"]
