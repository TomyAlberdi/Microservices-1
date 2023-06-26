# Microservices Project
This is a backend microservices project for a simple Notes App.
## Features
* Register, log into and delete accounts. Search by ID and email.
* Create, update and delete individual notes. Search by content and user.
## Technologies
This project was made in Java with Spring Boot and Spring Cloud frameworks.
Tech stack diagram:
![tech stack diagram](https://github.com/TomyAlberdi/Microservices-1/assets/66546046/307194c0-9768-41f7-bcc0-8fade2669557)
#### GitHub Config & Spring Cloud Config
Microservice used for externalized configuration in a distributed system. The configuration files are hosted in this [GitHub Repository](https://github.com/TomyAlberdi/spring-cloud-config-Microservices-1)
#### Service Discovery: Eureka Server & Client
Used for tracking and communication between microservices.
#### Spring Cloud Gateway
Provides a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as: security, monitoring/metrics, and resiliency.
This allows all instances of other microservices to be accessed through the same port.
#### Load Balancer
Allows the microservices to generate new instances of themselves in case of an increase in demand.
#### Feign Clients
Used for facilitating requests between microservices.
#### Circuit Breaker
Allows each microservice to have fallback methods in case of a system error.
### Dockerized Services:
#### MySQL Database
Used to store and retrieve user data and notes.
#### Zipkin
Allows easy tracking of every request made at the server.
#### RabbitMQ
Allows asynchronous messaging between microservices.
