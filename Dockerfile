FROM openjdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.securirity.egd=file:/dev/./urandom", "-jar" , "/app.jar"]
EXPOSE 8080