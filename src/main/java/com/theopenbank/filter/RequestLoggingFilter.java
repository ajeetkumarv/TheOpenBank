package com.theopenbank.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();

        String requestId = UUID.randomUUID().toString();
        MDC.put("reqId", requestId);

        log.info("[REQ][{}] {} {}{}", requestId, method, uri, query != null ? "?" + query : "");

        // Log basic request info
        log.info("[REQ][{}] {} {}{}",
                requestId,
                method,
                uri,
                query != null ? "?" + query : ""
        );

        filterChain.doFilter(request, response);

        // Time taken
        long duration = System.currentTimeMillis() - startTime;

        // Log after completion
        log.info("[RES][{}] {} {} - {} ms - status {}",
                requestId,
                method,
                uri,
                duration,
                response.getStatus()
        );

        MDC.clear();
    }
}
