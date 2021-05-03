From openjdk:8
EXPOSE 9031
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","app.jar"]