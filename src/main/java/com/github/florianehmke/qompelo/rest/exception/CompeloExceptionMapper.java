package com.github.florianehmke.qompelo.rest.exception;

import com.github.florianehmke.qompelo.domain.CompeloException;
import com.github.florianehmke.qompelo.domain.game.GameNotFoundException;
import com.github.florianehmke.qompelo.domain.player.PlayerNotFoundException;
import com.github.florianehmke.qompelo.domain.project.ProjectNotFoundException;
import com.github.florianehmke.qompelo.domain.project.ProjectPasswordWrongException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class CompeloExceptionMapper implements ExceptionMapper<CompeloException> {

  private static final Map<Class, Response.Status> STATUS_CODE_MAP = new HashMap<>();

  static {
    STATUS_CODE_MAP.put(ProjectNotFoundException.class, NOT_FOUND);
    STATUS_CODE_MAP.put(ProjectPasswordWrongException.class, FORBIDDEN);
    STATUS_CODE_MAP.put(PlayerNotFoundException.class, NOT_FOUND);
    STATUS_CODE_MAP.put(GameNotFoundException.class, NOT_FOUND);
    STATUS_CODE_MAP.put(ForbiddenProjectException.class, FORBIDDEN);
  }

  @Override
  public Response toResponse(CompeloException exception) {
    var status = STATUS_CODE_MAP.get(exception.getClass());
    if (status == null) {
      throw new RuntimeException("unregistered exception: " + exception.getClass().getName());
    }

    var error = new ApiError(status.getStatusCode(), exception.getMessage());
    return Response.status(status).entity(error).type(APPLICATION_JSON).build();
  }
}
