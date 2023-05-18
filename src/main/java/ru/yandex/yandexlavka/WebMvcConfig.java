package ru.yandex.yandexlavka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.yandex.yandexlavka.controller.RateLimiterInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    private final RateLimiterInterceptor rateLimiterInterceptor;
    @Autowired
    public WebMvcConfig(RateLimiterInterceptor rateLimiterInterceptor){
        this.rateLimiterInterceptor = rateLimiterInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimiterInterceptor);
    }
}
