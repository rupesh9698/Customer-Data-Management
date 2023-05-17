FROM openjdk:17-jdk-slim-buster AS builder

RUN apt-get update -y
RUN apt-get install -y binutils

WORKDIR /app

COPY . .

RUN ./gradlew build -i --stacktrace
RUN ./gradlew jlink -i --stacktrace

# lightweight image
FROM debian:stretch-slim

EXPOSE 5424

COPY --from=build /build/libs/Customer-Data-Management-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]