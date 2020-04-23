package br.com.cooperativismo.application.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VotoResponse {

    String response;

    public VotoResponse(String response) {
        this.response = response;
    }

}
