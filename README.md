# Jaeger Configuration Article
https://medium.com/cloud-native-daily/how-to-send-traces-from-spring-boot-to-jaeger-229c19f544db

| Service    | Calls                         |
|------------|------------------------------|
| Service-A  | Service-B                    |
| Service-B  | Service-E                    |
| Service-C  | Service-F → Service-G        |
| Service-D  | Service-H, Service-I (Parallel) |
| Service-E  | Service-D → Service-B        |
| Service-F  | (No dependencies)            |
| Service-G  | (No dependencies)            |
| Service-H  | (No dependencies)            |
| Service-I  | (No dependencies)            |
| Service-J  | Service-C                    |

# Run RabbitMQ using Docker
```docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.12-management```

# Run Jaeger
```docker-compose up -d```
