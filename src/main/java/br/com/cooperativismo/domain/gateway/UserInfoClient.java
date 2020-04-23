package br.com.cooperativismo.domain.gateway;

import br.com.cooperativismo.domain.gateway.response.ResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "validarCpf",
        url = "${app.user_info.url}",
        path = "/users",
        configuration = UserInfoClientConfig.class,
        fallbackFactory = UserInfoFallbackFactory.class)
public interface UserInfoClient {

    @GetMapping(value = "/{cpf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseClient validarCpf(@PathVariable String cpf);

}