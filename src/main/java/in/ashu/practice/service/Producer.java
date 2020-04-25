package in.ashu.practice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Producer {

	private static final String TOPIC = "users";

	private final KafkaTemplate<String, String> kafkaTemplate;

	public Producer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		log.info(String.format("$$ -> Producing message --> %s", message));
		kafkaTemplate.send(TOPIC, message);
	}
}