package br.com.cooperativismo.infrastructure.config;

import br.com.cooperativismo.CooperativismoApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executor;

@Slf4j
@EnableCaching
@EnableAsync
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@EnableSwagger2
@Configuration
public class ApiDocumentationConfig {

    private static final String TITTLE = "Cooperativismo API";
    private static final String DESCRIPTION = "System to register guidelines and votes.";

    @Bean
    Docket apiDocket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors
                        .basePackage(CooperativismoApplication.class.getPackageName()))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    ApplicationEventMulticaster applicationEventMulticaster(ThreadPoolTaskExecutor taskExecutor) {
        var eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(eventsTasksExecutor());
        return eventMulticaster;
    }

    private Executor eventsTasksExecutor() {
        log.info("creating Thread pool events");
        var threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Events-");
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.initialize();
        log.info("Thread pool events created");
        return threadPoolTaskExecutor;
    }

    @Bean
    ApiInfo apiInfo(@Value("${springfox.documentation.info.version}") String version) {
        return new ApiInfoBuilder().title(TITTLE).description(DESCRIPTION).version(version)
                .build();
    }
}
