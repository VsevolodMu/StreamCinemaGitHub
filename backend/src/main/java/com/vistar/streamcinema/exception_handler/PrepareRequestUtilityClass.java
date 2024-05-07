package com.vistar.streamcinema.exception_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@UtilityClass
public class PrepareRequestUtilityClass {
    public static ObjectMapper getJsonExceptionMapper() {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper;
    }

    public static ExceptionResponse getExceptionResponse(HttpServletResponse response, ResponseEntity<ExceptionResponse> responseEntity) {
        ExceptionResponse exceptionResponse = responseEntity.getBody();
        response.setContentType(APPLICATION_JSON_VALUE);
        if (exceptionResponse != null) {
            response.setStatus(exceptionResponse.getStatus().value());
        }
        return exceptionResponse;
    }
}

