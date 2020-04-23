package br.com.cooperativismo.application.controller;

import br.com.cooperativismo.application.controller.api.PautaAPI;
import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.domain.entity.Pauta;
import br.com.cooperativismo.domain.services.PautaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PautaController implements PautaAPI {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Override
    public Pauta savePauta(PautaRequest request) {
        return this.pautaService.savePauta(request);
    }


}
