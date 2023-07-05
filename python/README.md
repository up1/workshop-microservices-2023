# Workshop python flask
* [Prometheus Flask exporter](https://github.com/rycus86/prometheus_flask_exporter)
* [Grafana dashboard](https://github.com/rycus86/prometheus_flask_exporter/tree/master/examples/sample-signals)

```
$pip install -r requirements.txt
$python server.py
```

## Run with docker compose
```
$docker-compose build
$docker-compose up -d
$docker-compose ps
```

List of urls
* API => http://localhost:5000
* Metric => http://localhost:5000/metrics