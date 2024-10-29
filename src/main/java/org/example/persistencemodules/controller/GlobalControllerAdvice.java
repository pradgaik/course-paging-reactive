package org.example.persistencemodules.controller;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import org.example.persistencemodules.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException( EntityNotFoundException e, WebRequest request) {
    CustomErrorResponse errorResponse = new CustomErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
    errorResponse.setMessage(e.getMessage());
    errorResponse.setPath(request.getDescription(false));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
