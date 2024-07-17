This application demonstrates exploration of hexagonal architecture with microservices.

Modules use Spring Boot stack with Consul as service discovery and Keycloak as Authorization server.

![application](docs/application.png)

## Running the application

Build images with buildpack:

```
./frontend-htmx/mvnw spring-boot:build-image -f ./frontend-htmx/pom.xml && \
./product-catalog/gradlew -p ./product-catalog bootBuildImage && \
./offer-scraper/gradlew -p ./offer-scraper bootBuildImage
```

Then run `docker compose up -d`

Configure your hosts file with additional mappings (keycloak is mandatory):

```
127.0.0.1   keycloak
127.0.0.1   consul
127.0.0.1   rabbitmq
```

Frontend application is running on port `8081`. Keycloak test user is `user1 : pass1`

You can access:
- Frontend application on port `8081`
- Keycloak admin console on port `8080` with user `admin : admin`
- Consul management console on port `8085`
- RabbitMQ console on port `15672` with user `guest : guest`
- Grafana on port `3000` with user `admin : grafana`
- Prometheus on port `9090`
