FROM openjdk:8-jdk-alpine
ENV BUILD_VERSION 1.0

ENV DB_HOST localhost
ENV DB_PORT 5432
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} bumblebee.jar

EXPOSE 8080:8080
EXPOSE 8090:8090

ENTRYPOINT exec java $JAVA_OPTS -Ddb.host=$DB_HOST -Ddb.port=$DB_PORT -jar /bumblebee.jar