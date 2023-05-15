package ru.yandex.yandexlavka.controller;


import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.yandex.yandexlavka.controller.error.TooManyRequestsException;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@SuppressWarnings("UnstableApiUsage")
public class RateLimiterInterceptor implements HandlerInterceptor {
    private static final Integer DEFAULT_RPS_LIMIT = 10;

    private final ConcurrentMap<Method, RateLimiter> requestRateLimiters =
            new ConcurrentHashMap<>();


    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        Method requestMethod = ((HandlerMethod) handler).getMethod();

        requestRateLimiters.putIfAbsent(requestMethod, RateLimiter.create(DEFAULT_RPS_LIMIT));
        RateLimiter rateLimiter = requestRateLimiters.get(requestMethod);
        if(rateLimiter.tryAcquire()){
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            throw new TooManyRequestsException( DEFAULT_RPS_LIMIT);
        }
    }
}
