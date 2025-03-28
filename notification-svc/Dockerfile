FROM openjdk:21

COPY target/notification-svc-*.jar app.jar
                      # enviroment variables
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]