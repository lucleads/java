package com.pruebas.content.exception_handler.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private String message;

    private String path;

    private Object value;

}
