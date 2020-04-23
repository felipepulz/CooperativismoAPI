package br.com.cooperativismo.domain.gateway;

import br.com.cooperativismo.domain.gateway.interceptor.ParamsInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

class UserInfoClientConfig {

    UserInfoClientConfig() {
    }

    @Bean
    RequestInterceptor createAppendableInterceptor() {
        return new ParamsInterceptor();
    }

}
