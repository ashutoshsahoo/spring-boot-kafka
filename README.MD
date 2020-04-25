# Spring Boot Apache Kafka Example

## Steps

- Set docker host ip
```sh
$ export DOCKER_HOST_IP=127.0.0.1
``` 

- Start Kafka
	
```
$ docker-compose -f zk-single-kafka-single.yml up -d
```
Zookeeper will be available at `$DOCKER_HOST_IP:2181`
Kafka will be available at `$DOCKER_HOST_IP:9092`

- Create topic `users`

```sh

# 
$ docker exec -it $(docker ps -q --filter "label=name=kafka") /bin/bash
# Run following command if using git bash
# docker exec -it $(docker ps -q --filter "label=name=kafka") //bin//bash
$ cd /usr/bin
$ ./kafka-topics --create --bootstrap-server localhost:9092 \
 	--replication-factor 1 --partitions 1 --topic users
```

- Optional step:
```sh
# list topics
$ ./kafka-topics.sh --list --bootstrap-server localhost:9092
# Send some messages
$ ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic users
This is a message
This is another message
# press ctrl+c
# Start a consumer
$ ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
This is a message
This is another message
# press ctrl+c
$ exit
```

- Start spring-boot-kafka application and test
```sh
$ mvn spring-boot:run
$ curl --request POST 'localhost:8080/kafka/publish?message=Hello%20from%20spring%20boot'

```
Check application console for following lines

```
2020-04-25 17:20:28.301  INFO 2088 --- [nio-8080-exec-6] in.ashu.practice.service.Producer        : $$ -> Producing message --> Hello from spring boot
2020-04-25 17:20:28.314  INFO 2088 --- [ntainer#0-0-C-1] in.ashu.practice.service.Consumer        : $$ -> Consumed Message -> Hello from spring boot
```




- Stop Kafka and remove containers.

```
$ docker-compose -f zk-single-kafka-single.yml down
```

## Reference:
- [Apache Kafka docker compose](https://github.com/simplesteph/kafka-stack-docker-compose)
- [Apache Kafka Quick start](https://kafka.apache.org/quickstart)
- [Spring boot Kafka configuration](https://dzone.com/articles/magic-of-kafka-with-spring-boot)
- [Spring for Apache Kafka](https://docs.spring.io/spring-kafka/reference/html/)