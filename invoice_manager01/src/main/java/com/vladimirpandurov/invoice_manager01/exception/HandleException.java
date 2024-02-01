package com.vladimirpandurov.invoice_manager01.exception;

import com.vladimirpandurov.invoice_manager01.domain.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class HandleException extends ResponseEntityExceptionHandler implements ErrorController {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        return new ResponseEntity<>(
                HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .reason(fieldMessage)
                .developerMessage(ex.getMessage())
                .status(HttpStatus.resolve(status.value()))
                .statusCode(status.value())
                .build(), status
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .reason(ex.getMessage())
                .developerMessage(ex.getMessage())
                .status(HttpStatus.resolve(statusCode.value()))
                .statusCode(statusCode.value())
                .build(), statusCode
        );
    }
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<HttpResponse> apiException(ApiException exception){
        return new ResponseEntity<>(
                HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .reason(exception.getMessage())
                .developerMessage(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
