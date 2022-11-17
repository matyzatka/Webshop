package com.ao.webshop.exceptions;

import com.ao.webshop.models.dto.ErrorResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class WebshopExceptionHandler {

  @ExceptionHandler()
  public ErrorResponseDto handleWebshopException(WebshopException e) {
    return new ErrorResponseDto(e.getStatus(), e.getPath(), e.getMessage());
  }
}
