FROM openjdk:17-jdk-slim-buster

ADD target/kameleoon.jar kameleoon.jar

CMD ["java","-jar","kameleoon.jar"]