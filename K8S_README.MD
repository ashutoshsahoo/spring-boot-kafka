# Start Apache Kafka in kubernetes


```sh

kubectl -f kubernetes.yaml apply 

export POD_NAME=$(kubectl get pods --namespace default -l "app=kafka" -o jsonpath="{.items[0].metadata.name}")

kubectl --namespace default exec -it $POD_NAME -- kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

kubectl --namespace default exec -it $POD_NAME -- kafka-topics.sh --list --bootstrap-server localhost:9092

kubectl --namespace default exec -it $POD_NAME -- kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test

kubectl --namespace default exec -it $POD_NAME -- kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning



mvn clean package

mvn -DskipTests fabric8:build fabric8:resource fabric8:deploy

mvn fabric8:undeploy

kubectl -f kubernetes.yaml delete
# Sometimes the PVC don't delete, that shows folllowing error on next restart

ERROR Fatal error during KafkaServer startup. Prepare to shutdown (kafka.server.KafkaServer)
kafka.common.InconsistentClusterIdException: The Cluster ID eyWR-NmhRtW9V1Z-0qfZGw doesn't match stored clusterId Some(qI_Zw_I3TcKN9eNzkkEC1A) in meta.properties. The broker is trying to join the wrong cluster. Configured zookeeper.connect may be wrong.
        at kafka.server.KafkaServer.startup(KafkaServer.scala:223)
        at kafka.server.KafkaServerStartable.startup(KafkaServerStartable.scala:44)
        at kafka.Kafka$.main(Kafka.scala:82)
        at kafka.Kafka.main(Kafka.scala)

# Solution
kubectl get pvc 
kubectl delete pvc kafka-data-kafka-0

```

### Reference

- [Bitnami Docker Kafka](https://github.com/bitnami/bitnami-docker-kafka)