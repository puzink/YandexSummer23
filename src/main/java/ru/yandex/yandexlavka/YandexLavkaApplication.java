package ru.yandex.yandexlavka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import ru.yandex.yandexlavka.controller.RateLimiterInterceptor;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
public class YandexLavkaApplication {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource resourceBundle = new ReloadableResourceBundleMessageSource();
        resourceBundle.setBasename("classpath:bean_validation_messages");
        resourceBundle.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return resourceBundle;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(YandexLavkaApplication.class, args);
    }

}
