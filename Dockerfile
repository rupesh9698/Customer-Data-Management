FROM gradle:7.0.2-jdk17 AS build

COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 5424

COPY --from=build /build/libs/Customer-Data-Management-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]