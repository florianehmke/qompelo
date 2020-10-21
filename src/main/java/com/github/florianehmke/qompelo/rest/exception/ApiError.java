package com.github.florianehmke.qompelo.rest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiError {
  private final Integer code;
  private final String message;
}
