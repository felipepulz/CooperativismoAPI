package br.com.cooperativismo.infrastructure.kafka;

import br.com.cooperativismo.application.events.KafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PautaEventListener {

    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;
    private final String topic;

    @Autowired
    public PautaEventListener(KafkaTemplate<String, KafkaEvent> kafkaTemplate,
                              @Value("${app.kafka.topics.pauta}") String changeCustomerTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = changeCustomerTopic;
    }

    @EventListener(KafkaEvent.class)
    public void sendPauta(KafkaEvent event) {
        log.info("Pauta for {}. Data {}", event.getRequest(), event.getResponse());
        kafkaTemplate.send(topic, event.hashCode() + "", event);
    }
}
