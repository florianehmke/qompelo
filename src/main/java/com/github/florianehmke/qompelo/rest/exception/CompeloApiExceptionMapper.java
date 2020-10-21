package com.github.florianehmke.qompelo.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class CompeloApiExceptionMapper implements ExceptionMapper<CompeloApiException> {

  @Override
  public Response toResponse(CompeloApiException exception) {
    return Response.status(exception.getCode())
        .entity(exception.toApiError())
        .type(APPLICATION_JSON)
        .build();
  }
}
