FROM gradle:7.0.2-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon clean build

FROM amazoncorretto:11.0.12-alpine
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
