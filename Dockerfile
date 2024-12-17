# Builder
FROM maven:3.8.3-openjdk-17 AS build
COPY --chown=maven:maven . /home/maven/project
WORKDIR /home/maven/project

ENV POSTGRES_HOST=db
ENV POSTGRES_DB=sample_db
ENV POSTGRES_USER=sample_user
ENV POSTGRES_PASSWORD=sample_password

RUN mvn clean install -DskipTests

# Runner
FROM openjdk:17-jdk-slim
VOLUME /tmp

COPY --from=build /home/maven/project/target/*.jar app.jar
EXPOSE 13228
CMD ["java", "-jar", "/app.jar"]