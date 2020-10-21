package com.github.florianehmke.qompelo.rest.exception;

import lombok.Getter;

@Getter
public class CompeloApiException extends RuntimeException {

  private final Integer code;

  public CompeloApiException(Integer code, String message) {
    super(message);
    this.code = code;
  }

  public ApiError toApiError() {
    return new ApiError(code, getMessage());
  }
}
