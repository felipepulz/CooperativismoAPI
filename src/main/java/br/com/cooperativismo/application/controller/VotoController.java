package br.com.cooperativismo.application.controller;

import br.com.cooperativismo.application.controller.api.VotoAPI;
import br.com.cooperativismo.application.request.VotoRequest;
import br.com.cooperativismo.application.response.VotoResponse;
import br.com.cooperativismo.domain.services.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class VotoController implements VotoAPI {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @Override
    public VotoResponse saveVoto(VotoRequest request) {
        return this.votoService.saveVoto(request);
    }

}
