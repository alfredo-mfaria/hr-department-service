package com.example.hr.department.service.handler;

import com.example.hr.department.service.exception.DataConflictException;
import com.example.hr.department.service.exception.DataNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DataNotFoundException.class)
    public final ResponseEntity<ExceptionDTO> handleException(DataNotFoundException ex) {

        return new ResponseEntity<>(getExceptionDTO(ex.getReason()), ex.getStatus());
    }

    @ExceptionHandler(value = DataConflictException.class)
    public final ResponseEntity<ExceptionDTO> handleException(DataConflictException ex) {

        return new ResponseEntity<>(getExceptionDTO(ex.getReason()), ex.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionDTO> handleException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>(getExceptionDTO(getDefaultMessage(ex)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<ExceptionDTO> handleException(Exception ex) {

        return new ResponseEntity<>(getExceptionDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionDTO getExceptionDTO(String reason) {
        return ExceptionDTO.builder()
                .message(reason)
                .build();
    }

    private String getDefaultMessage(MethodArgumentNotValidException ex) {
        return Optional.ofNullable(ex.getBindingResult().getFieldError())
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Request validation error");
    }
}
