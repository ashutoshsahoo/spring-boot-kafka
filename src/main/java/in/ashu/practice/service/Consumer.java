package in.ashu.practice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@KafkaListener(topics = "users", groupId = "group_id")
	public void consume(String message) {
		log.info(String.format("$$ -> Consumed Message -> %s", message));
	}
}