package com.github.florianehmke.qompelo.core.project;

import com.github.florianehmke.qompelo.core.project.models.ProjectCreateRequest;
import com.github.florianehmke.qompelo.core.project.models.ProjectResponse;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import static com.github.florianehmke.qompelo.util.PasswordUtils.hashPassword;
import static com.github.florianehmke.qompelo.util.PasswordUtils.verifyPassword;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectController {

  @Inject ProjectMapper mapper;
  @Inject ProjectRepository projectRepository;

  @Inject JsonWebToken jwt;

  @GET
  public Collection<ProjectResponse> listAll() {
    System.out.println(jwt);
    return mapper.map(ProjectEntity.listAll());
  }

  @POST
  @Transactional
  public ProjectResponse post(@Valid ProjectCreateRequest request) {
    var project = new ProjectEntity();
    project.name = request.getName();
    project.passwordHash = hashPassword(request.getPassword());
    projectRepository.persist(project);
    return mapper.map(project);
  }

  public boolean authenticateProject(Long projectId, String password) {
    return projectRepository
        .findByIdOptional(projectId)
        .map(project -> verifyPassword(password, project.passwordHash))
        .orElse(false);
  }
}
