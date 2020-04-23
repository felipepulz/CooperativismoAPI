package br.com.cooperativismo.application.controller;

import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.data.PautaData;
import br.com.cooperativismo.domain.services.PautaService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ImportAutoConfiguration({WebConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {PautaController.class})
public class PautaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PautaService pautaService;

    @Test
    public void givenAnPautaMustReturnRequisitionSuccess() throws Exception {
        when(this.pautaService.savePauta(any(PautaRequest.class))).thenReturn(new PautaData().getPauta());
        mvc.perform(
                post("/pauta/save")
                        .content(ParserJSON.fromObjToJSON(new PautaRequest("Algum nome", "Algum descricao", 1)))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomePauta", is("Algum nome")));
    }

}
