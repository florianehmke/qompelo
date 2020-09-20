package com.github.florianehmke.qompelo.rest.endpoint.game;

import com.github.florianehmke.qompelo.domain.Game;
import com.github.florianehmke.qompelo.rest.endpoint.game.models.GameCreateRequest;
import com.github.florianehmke.qompelo.rest.endpoint.game.models.GameResponse;
import com.github.florianehmke.qompelo.rest.endpoint.project.ProjectSubResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource extends ProjectSubResource {

  @Inject GameMapper mapper;

  @POST
  public GameResponse create(@Valid GameCreateRequest request) {
    return mapper.map(Game.create(request.getName(), project));
  }

  @GET
  public Collection<GameResponse> listAll() {
    return mapper.map(project.games);
  }
}
