package com.github.florianehmke.qompelo.rest.endpoint.match;

import com.github.florianehmke.qompelo.rest.endpoint.game.GameSubResource;
import com.github.florianehmke.qompelo.rest.endpoint.match.mapper.MatchMapper;
import com.github.florianehmke.qompelo.rest.endpoint.match.mapper.TeamParameterMapper;
import com.github.florianehmke.qompelo.rest.endpoint.match.model.MatchCreateRequest;
import com.github.florianehmke.qompelo.rest.endpoint.match.model.MatchResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
public class MatchResource extends GameSubResource {

  @Inject MatchMapper matchMapper;
  @Inject TeamParameterMapper teamParameterMapper;

  @GET
  public Collection<MatchResponse> listAll() {
    return matchMapper.map(game.matches);
  }

  @POST
  @Transactional
  public MatchResponse create(@Valid MatchCreateRequest request) {
    return matchMapper.map(game.addMatch(teamParameterMapper.map(request.getTeams())));
  }
}
