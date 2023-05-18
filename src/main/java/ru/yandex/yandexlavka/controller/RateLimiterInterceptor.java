package ru.yandex.yandexlavka.controller;

import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.yandex.yandexlavka.controller.error.TooManyRequestsException;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ConfigurationProperties(prefix = "rate-limiter")
@SuppressWarnings("UnstableApiUsage")
public class RateLimiterInterceptor implements HandlerInterceptor {
    private final Properties properties;

    private final ConcurrentMap<Method, RateLimiter> requestRateLimiters =
            new ConcurrentHashMap<>();

    @ConstructorBinding
    public RateLimiterInterceptor(Properties properties){
        this.properties = properties;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        Method requestMethod = ((HandlerMethod) handler).getMethod();

        int limit = properties.getLimit();
        requestRateLimiters.putIfAbsent(requestMethod, RateLimiter.create(limit));
        RateLimiter rateLimiter = requestRateLimiters.get(requestMethod);
        if(rateLimiter.tryAcquire()){
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            throw new TooManyRequestsException(limit);
        }
    }

    @Data
    private static class Properties {
        private int limit = 10;
    }
}
