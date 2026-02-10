FROM maven:3-eclipse-temurin-25 as build

RUN useradd -m appuser

WORKDIR /app

RUN chown -R appuser:appuser /app

USER appuser

COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:25-jre

RUN useradd -m appuser

WORKDIR /app
USER appuser

COPY --from=build /app/target/backend-0.1.jar ./backend-0.1.jar

EXPOSE 8080
CMD ["java","-jar","backend-0.1.jar"]
