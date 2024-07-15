This application demonstrates exploration of hexagonal architecture with microservices.

Modules use Spring Boot stack with Consul as service discovery and Keycloak as Authorization server.

configure your hosts file with additional mappings:

```
127.0.0.1   keycloak
127.0.0.1   consul
```

![application](docs/application.png)
