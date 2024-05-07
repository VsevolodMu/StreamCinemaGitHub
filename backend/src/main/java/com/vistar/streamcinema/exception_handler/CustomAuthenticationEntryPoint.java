package com.vistar.streamcinema.exception_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.vistar.streamcinema.exception_handler.PrepareRequestUtilityClass.getExceptionResponse;
import static com.vistar.streamcinema.exception_handler.PrepareRequestUtilityClass.getJsonExceptionMapper;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ControllerExceptionHandler exceptionHandler;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseEntity<ExceptionResponse> responseEntity = exceptionHandler.handleException(authException);
        ExceptionResponse exceptionResponse = getExceptionResponse(response, responseEntity);
        ObjectMapper mapper = getJsonExceptionMapper();
        response.getWriter().write(mapper.writeValueAsString(exceptionResponse));
    }
}

