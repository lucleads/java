package com.pruebas.content.exception_handler;

import com.pruebas.content.exception_handler.infrastructure.controller.dto.ErrorDto;
import com.pruebas.content.exception_handler.infrastructure.controller.dto.ErrorsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    // ENTITY NOT FOUND EXCEPTION
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleNotFound(EntityNotFoundException e) {
        log.debug("Entity not found.", e);
    }

    // HIBERNATE VALIDATION ERROR
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorsDto handleConstraintViolations(ConstraintViolationException e) {
        final List<ErrorDto> errors = e.getConstraintViolations()
                .stream()
                .map(v -> ErrorDto.builder()
                        .message(v.getMessage())
                        .path(v.getPropertyPath().toString())
                        .value(v.getInvalidValue())
                        .build())
                .collect(Collectors.toList());

        return new ErrorsDto(errors);
    }

}

