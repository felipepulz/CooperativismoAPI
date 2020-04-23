package br.com.cooperativismo.application.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PautaResponse {

    String response;

    public PautaResponse(String response) {
        this.response = response;
    }

}
