FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/AIM_SGSN_SGSN_CEBU.xml ./test.xml
ENTRYPOINT ["java", "-jar", "/app.jar"]