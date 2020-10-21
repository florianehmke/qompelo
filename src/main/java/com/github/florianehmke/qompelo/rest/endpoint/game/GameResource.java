package com.github.florianehmke.qompelo.rest.endpoint.game;

import com.github.florianehmke.qompelo.domain.game.Game;
import com.github.florianehmke.qompelo.rest.endpoint.game.mapper.GameMapper;
import com.github.florianehmke.qompelo.rest.endpoint.game.model.GameCreateRequest;
import com.github.florianehmke.qompelo.rest.endpoint.game.model.GameResponse;
import com.github.florianehmke.qompelo.rest.endpoint.match.MatchResource;
import com.github.florianehmke.qompelo.rest.endpoint.project.ProjectSubResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource extends ProjectSubResource {

  @Inject GameMapper mapper;
  @Inject MatchResource matchResource;

  @POST
  public GameResponse create(@Valid GameCreateRequest request) {
    return mapper.map(project.addGame(request.getName()));
  }

  @GET
  public Collection<GameResponse> listAll() {
    return mapper.map(project.games);
  }

  @Path("{gameId}/matches")
  public MatchResource matchResource(@PathParam("gameId") Long gameId) {
    return (MatchResource) matchResource.withGame(Game.mustLoad(gameId));
  }
}
