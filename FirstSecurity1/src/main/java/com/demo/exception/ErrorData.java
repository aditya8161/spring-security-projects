package com.demo.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorData
{
    private String message;
    private String path;
    private LocalDateTime errorDateTime;
    private HttpStatus statusCode;

}
