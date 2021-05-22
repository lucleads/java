package com.pruebas.content.exception_handler.infrastructure.controller.dto;

import com.pruebas.content.exception_handler.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsDto {

    private List<ErrorDto> errors;

}
