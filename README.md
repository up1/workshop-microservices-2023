# Microservices workshop
* Develop
  * Spring Boot
  * NodeJS
  * Apache Kafka
* Working with Docker
* Observability
  * Distributed tracing
    * OpenTelemetry
    * Zipkin
  * Application metric
    * Prometheus
    * Grafana
* Testing
  * API testing with Postman
  * Contract testing with Pact


## Step 1 :: Order service
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Spring Boot 3


Step to build and run service
```
$./mvnw clean package
$./mvnw spring-boot:run
```

With Docker
```
$docker compose build
$docker compose up -d order
$docker compose ps
```

Stop all container
```
$docker compose down
```

Access to API specification with Swagger
* http://localhost:8080/swagger-ui/index.html

Access to service
* GET http://localhost:8080/order/all
* POST http://localhost:8080/order