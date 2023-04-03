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
$docker compose build order
$docker compose up -d order
$docker compose ps
$docker compose logs --follow
```

Stop all container
```
$docker compose down
```

Access to API specification with [Swagger v2](https://springdoc.org/v2/)
* http://localhost:8080/swagger-ui/index.html

Access to service
* GET http://localhost:8080/order/all
* POST http://localhost:8080/order

## Observability

### Distributed Tracing with Zipkin
  * http://localhost:9411/

```
$docker compose up -d zipkin
$docker compose ps
$docker compose logs --follow
```

### Application Metric with Actuator and [Prometheus](https://prometheus.io/)
  * http://localhost:8080/actuator
  * http://localhost:8080/actuator/health
  * http://localhost:8080/actuator/prometheus

```
$docker compose up -d prometheus
$docker compose ps
$docker compose logs --follow
```

Open prometheus server
* http://localhost:9090
  * Go to menu `Status => Targets`

Dashboard with [Grafana](https://grafana.com/)
* [More dashboard](https://grafana.com/grafana/dashboards/)
```
$docker compose up -d grafana
$docker compose ps
$docker compose logs --follow
```

Open prometheus server
* http://localhost:3000
  * user=admin
  * password=admin

### API testing with Postman
* [newman](https://www.npmjs.com/package/newman)

```
$npm install -g newman

$cd testing/api-postman
$newman run order-service.postman_collection.json
```