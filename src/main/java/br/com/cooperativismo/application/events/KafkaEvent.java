package br.com.cooperativismo.application.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"source", "timestamp"})
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaEvent extends ApplicationEvent {

    Map<String, Object> request;
    Object response;
    String provider;
    long createdAt;

    public KafkaEvent(String mensagem) {
        super(mensagem);
        this.provider = "Pauta";
        this.createdAt = now();
        this.request = new HashMap<>();
        this.request.put("message", mensagem);
    }

    private long now() {
        return System.currentTimeMillis();
    }

    public void registerData(Object value) {
        response = value;
    }

}
