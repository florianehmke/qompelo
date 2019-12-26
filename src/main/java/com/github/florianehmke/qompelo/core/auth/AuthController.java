package com.github.florianehmke.qompelo.core.auth;

import com.github.florianehmke.qompelo.core.auth.models.AuthRequest;
import com.github.florianehmke.qompelo.core.auth.models.AuthResponse;
import com.github.florianehmke.qompelo.core.auth.models.TokenData;
import com.github.florianehmke.qompelo.core.project.ProjectController;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

  @Inject ProjectController projectController;
  @Inject AuthService authService;

  @POST
  public AuthResponse login(@Valid AuthRequest authRequest) {
    var authenticated =
        projectController.authenticateProject(
            authRequest.getProjectId(), authRequest.getPassword());

    if (!authenticated) {
      throw new WebApplicationException(Response.Status.FORBIDDEN);
    }

    var tokenData = TokenData.builder().projectId(authRequest.getProjectId()).build();
    var accessToken = authService.createToken(tokenData);
    return AuthResponse.builder().token(accessToken).build();
  }
}
