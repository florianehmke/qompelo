package com.github.florianehmke.qompelo.rest.exception;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

public class ForbiddenProjectException extends CompeloApiException {

  public ForbiddenProjectException() {
    super(FORBIDDEN.getStatusCode(), "not your project");
  }
}
