FROM mcr.microsoft.com/java/jre:8-zulu-alpine as builder
WORKDIR app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM mcr.microsoft.com/java/jre:8-zulu-alpine
WORKDIR app

COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./

ENTRYPOINT ["java","-Dspring.profiles.active=docker", "org.springframework.boot.loader.JarLauncher"]