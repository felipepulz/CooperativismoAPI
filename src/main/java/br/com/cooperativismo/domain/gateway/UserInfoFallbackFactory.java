package br.com.cooperativismo.domain.gateway;

import br.com.cooperativismo.domain.gateway.response.ResponseClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class UserInfoFallbackFactory implements FallbackFactory<UserInfoClient> {
    @Override
    public UserInfoClient create(Throwable cause) {
        log.error("Error checking CPF.", cause);
        return cpf -> new ResponseClient("UNABLE_TO_VOTE");
    }
}
