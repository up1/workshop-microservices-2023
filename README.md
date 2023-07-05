# Microservices workshop
* Develop
  * Spring Boot (order service)
  * NodeJS (inventory service)
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

## Step 1 :: Order service
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Spring Boot 3


Step to build and run service
```
$cd order
$./mvnw clean package
$./mvnw spring-boot:run
$cd ..
```

With Docker
```
$docker compose build order
$docker compose up -d order
$docker compose ps
$docker compose logs --follow
```

Access to API specification with [Swagger v2](https://springdoc.org/v2/)
* http://localhost:8080/swagger-ui/index.html

Access to service
* GET http://localhost:8080/order/all
* POST http://localhost:8080/order

Create inventory service
```
$docker compose build inventory
$docker compose up -d inventory
$docker compose ps
$docker compose logs --follow
```

Access to service
* GET http://localhost:4000/healthz
* GET http://localhost:4000/stock/deduct/1

## Observability

### Distributed Tracing with Zipkin

```
$docker compose up -d zipkin
$docker compose ps
$docker compose logs --follow
```
Open Zipkin server in url=http://localhost:9411/


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

Open Grafana server
* http://localhost:3000
  * user=admin
  * password=admin

## Working with Grafana Platform
* Log aggregation with [Loki](https://grafana.com/oss/loki/)
* Dashboard with [Grafana](https://grafana.com)

Log aggregation with Loki
```
$docker compose up -d loki
$docker compose ps
$docker compose logs --follow
```

Dashboard with Grafana
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
