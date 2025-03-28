FROM amazoncorretto:17

COPY target/smart-wallet-application-*.jar app.jar
                     #here I can add my enviroment variables
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]