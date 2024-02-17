package org.lamberm.AtiperaTask.error.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<RestErrorResponse> handleHttpClientErrorException(HttpClientErrorException e){
        if (e.getStatusCode() == HttpStatusCode.valueOf(404)){
            return ResponseEntity.status(HttpStatusCode.valueOf(e.getStatusCode().value()))
                    .body(new RestErrorResponse(e.getStatusCode().value(), "Github user does not exist"));
        }
        if (e.getStatusCode() == HttpStatusCode.valueOf(403)){
            return ResponseEntity.status(HttpStatusCode.valueOf(e.getStatusCode().value()))
                    .body(new RestErrorResponse(HttpStatusCode.valueOf(503).value(), "Service Unavailable"));
        }
        else {
            return ResponseEntity.status(HttpStatusCode.valueOf(e.getStatusCode().value()))
                    .body(new RestErrorResponse(HttpStatusCode.valueOf(500).value(), "Internal Server Error"));
        }
    }
}
