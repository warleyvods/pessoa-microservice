FROM openjdk:17

EXPOSE 8081

WORKDIR /app

ENV SCOPE="prod"

ENV PG_DB="jdbc:postgresql://db:5432/banco"

COPY build/libs/pessoa.jar /app/pessoa.jar

ENTRYPOINT ["java", "-jar", "pessoa.jar"]
