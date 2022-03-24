# Extend

Extend is a simple project showing some basic functionalities of the extend API services found at https://developer.paywithextend.com/#extend-api
It exposes API endpoints, which will use the extend API services.

# Requirements

This project has a requirement that a Redis server is running locally on port 6379. The default settings may be used.
To configure Redis to use a different host or port, please edit the application.properties file.
The deafult is localhost:6379.

A username and password for the Extend API Services is also required.

# Run

This is a spring boot project using maven, so please us maven to run the project.
```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8080
```

Note that you can run the application on multiple ports at the same time and Redis will be used to share session data.
