FROM amazoncorretto:17.0.5
ADD . /app
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./target/WalletApi-0.0.1-SNAPSHOT.jar"]