package br.com.cooperativismo.domain.services;

import br.com.cooperativismo.application.request.VotoRequest;
import br.com.cooperativismo.application.response.VotoResponse;
import org.springframework.validation.annotation.Validated;

@Validated
public interface VotoService {

    VotoResponse saveVoto(VotoRequest request);

}
