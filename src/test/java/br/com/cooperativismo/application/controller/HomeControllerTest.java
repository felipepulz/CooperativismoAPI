package br.com.cooperativismo.application.controller;

import br.com.cooperativismo.infrastructure.config.ApiDocumentationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
@ImportAutoConfiguration(ApiDocumentationConfig.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenAValidProductIsFoundThenReturnHTTP200() throws Exception {
        mvc.perform(
                get("/")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Name").exists())
                .andExpect(jsonPath("$.Version").exists());
    }

}