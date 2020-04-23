package br.com.cooperativismo.domain.services;

import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.domain.entity.Pauta;
import org.springframework.validation.annotation.Validated;

@Validated
public interface PautaService {

    Pauta savePauta(PautaRequest request);

}
