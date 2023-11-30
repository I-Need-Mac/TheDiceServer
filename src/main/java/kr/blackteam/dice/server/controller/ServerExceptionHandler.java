package kr.blackteam.dice.server.controller;

import kr.blackteam.dice.server.exception.DiceDurabilityException;
import kr.blackteam.dice.server.exception.DiceNotFoundException;
import kr.blackteam.dice.server.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerExceptionHandler {

  @ExceptionHandler(PlayerNotFoundException.class)
  public ResponseEntity<String> handlePlayerNotFoundException(PlayerNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DiceNotFoundException.class)
  public ResponseEntity<String> handleDiceNotFoundException(DiceNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DiceDurabilityException.class)
  public ResponseEntity<String> handleDiceDurabilityException(DiceDurabilityException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
    // Extract field errors and create a meaningful response
    StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      errorMessage.append(fieldError.getField()).append(" ").append(fieldError.getDefaultMessage())
          .append("; ");
    }
    return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception e) {
    return new ResponseEntity<>("An error occurred: " + e.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
