FROM openjdk:11-jre-slim-buster
RUN apt-get update && apt-get install curl -y
COPY ./target/backend-springboot-book.jar backend-springboot-book.jar
ENTRYPOINT ["java","-jar","backend-springboot-book.jar"]