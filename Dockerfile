FROM openjdk:8

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} order.jar


ENTRYPOINT [ "java" ,"-jar" ,"order.jar"]

EXPOSE 8086