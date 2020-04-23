package br.com.cooperativismo.domain.gateway.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseClient {

    String status;

}
