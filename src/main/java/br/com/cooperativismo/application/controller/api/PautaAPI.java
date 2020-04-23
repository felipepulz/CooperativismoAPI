package br.com.cooperativismo.application.controller.api;

import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.application.response.PautaResponse;
import br.com.cooperativismo.domain.entity.Pauta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@Validated
@RequestMapping(path = "/pauta", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface PautaAPI {

    @ApiOperation(value = "Request to save an pauta.", httpMethod = "POST",
            response = PautaResponse.class,
            responseContainer = "object",
            notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pauta found."),
            @ApiResponse(code = 500, message = "Server error.")
    })
    @PostMapping(path = "/save")
    Pauta savePauta(@Valid @RequestBody PautaRequest request);

}
	

