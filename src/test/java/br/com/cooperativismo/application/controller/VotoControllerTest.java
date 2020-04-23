package br.com.cooperativismo.application.controller;

import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.application.request.VotoRequest;
import br.com.cooperativismo.application.response.VotoResponse;
import br.com.cooperativismo.domain.entity.Voto;
import br.com.cooperativismo.domain.services.VotoService;
import br.com.cooperativismo.infrastructure.config.WebConfig;
import br.com.cooperativismo.infrastructure.util.ParserJSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ImportAutoConfiguration({WebConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VotoController.class})
public class VotoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VotoService votoService;

    @Test
    public void givenAnVotoMustReturnRequisitionSuccess() throws Exception {
        when(this.votoService.saveVoto(any(VotoRequest.class))).thenReturn(new VotoResponse("Voto registrado com sucesso!"));
        mvc.perform(
                post("/voto/save")
                        .content(ParserJSON.fromObjToJSON(new VotoRequest("", Voto.OpcoesVoto.SIM, "84868050079")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is("Voto registrado com sucesso!")));
    }



}
