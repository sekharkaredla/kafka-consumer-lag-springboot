docker-compose -f .\kafka_setup.yml up -d


cd opt/kafka_2.13-2.8.1/bin


kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic temp_topic

kafka-topics.sh --list --zookeeper zookeeper:2181


kafka-consumer-groups.sh --bootstrap-server kafka:9092 --describe --group temp_group


kafka-console-producer.sh --broker-list kafka:9092 --topic temp_topic


kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic temp_topic --from-beginning --group temp_group