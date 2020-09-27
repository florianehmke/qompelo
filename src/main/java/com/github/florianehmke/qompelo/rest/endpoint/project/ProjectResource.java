package com.github.florianehmke.qompelo.rest.endpoint.project;

import com.github.florianehmke.qompelo.domain.player.Player;
import com.github.florianehmke.qompelo.domain.project.Project;
import com.github.florianehmke.qompelo.rest.endpoint.game.GameResource;
import com.github.florianehmke.qompelo.rest.endpoint.player.mapper.PlayerMapper;
import com.github.florianehmke.qompelo.rest.endpoint.player.model.PlayerResponse;
import com.github.florianehmke.qompelo.rest.endpoint.project.mapper.ProjectMapper;
import com.github.florianehmke.qompelo.rest.endpoint.project.model.ProjectCreateRequest;
import com.github.florianehmke.qompelo.rest.endpoint.project.model.ProjectResponse;
import com.github.florianehmke.qompelo.rest.exception.ForbiddenProjectException;
import com.github.florianehmke.qompelo.rest.security.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/projects")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed(Roles.AUTHENTICATED)
public class ProjectResource {

  @Inject ProjectMapper projectMapper;
  @Inject PlayerMapper playerMapper;
  @Inject GameResource gameResource;
  @Inject Player currentPlayer;

  @GET
  public Collection<ProjectResponse> listAll() {
    return projectMapper.map(Project.listAll());
  }

  @GET
  @Path("mine")
  public Collection<ProjectResponse> mine() {
    return projectMapper.map(currentPlayer.projects);
  }

  @GET
  @Path("{projectId}/players")
  public Collection<PlayerResponse> players(@PathParam("projectId") Long projectId) {
    return playerMapper.map(Project.mustLoad(projectId).players);
  }

  @POST
  @Transactional
  public ProjectResponse createProject(@Valid ProjectCreateRequest request) {
    return projectMapper.map(Project.create(request.getName(), request.getPassword()));
  }

  @Path("{projectId}/games")
  public GameResource gameResource(@PathParam("projectId") Long projectId) {
    return (GameResource) gameResource.withProject(loadProject(projectId));
  }

  public Project loadProject(Long projectId) {
    if (currentPlayer == null || !currentPlayer.projectIds().contains(projectId)) {
      throw new ForbiddenProjectException();
    }
    return Project.mustLoad(projectId);
  }
}
