package br.com.cooperativismo.application.controller;

import br.com.cooperativismo.infrastructure.config.ApiDocumentationConfig;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
@ImportAutoConfiguration(ApiDocumentationConfig.class)
public class ApplicationExceptionHandlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HomeController controller;


    @SneakyThrows
    @Test
    public void handleMethodArgumentNotValidTest() {
        var exception = mock(MethodArgumentNotValidException.class);

        when(exception.getMessage()).thenReturn("mocked message");
        when(exception.getBindingResult()).thenReturn(mock(BindingResult.class));

        doAnswer(invocation -> {
            throw exception;
        }).when(controller).index();

        mvc.perform(
                get("/")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessages").exists());
    }

    @SneakyThrows
    @Test
    public void handleBadRequestTest() {
        var exception = spy(new ConstraintViolationException("error", Set.of()));

        doReturn("mocked message").when(exception).getMessage();
        doThrow(exception).when(controller).index();

        mvc.perform(
                get("/")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessages").exists());
    }

    @SneakyThrows
    @Test
    public void handlerInternalServerErrorTest() {
        doThrow(new RuntimeException("error")).when(controller).index();

        mvc.perform(
                get("/")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessages").exists());
    }
}
