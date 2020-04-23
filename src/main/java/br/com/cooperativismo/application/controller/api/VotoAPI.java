package br.com.cooperativismo.application.controller.api;

import br.com.cooperativismo.application.request.VotoRequest;
import br.com.cooperativismo.application.response.VotoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api
@Validated
@RequestMapping(path = "/voto", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface VotoAPI {

    @ApiOperation(value = "Request to save an voto.", httpMethod = "POST",
            response = VotoResponse.class,
            responseContainer = "object",
            notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Voto found."),
            @ApiResponse(code = 500, message = "Server error.")
    })
    @PostMapping(path = "/save")
    VotoResponse saveVoto(@Valid @RequestBody VotoRequest request);

}
