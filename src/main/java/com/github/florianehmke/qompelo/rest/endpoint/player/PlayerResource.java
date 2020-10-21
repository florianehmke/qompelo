package com.github.florianehmke.qompelo.rest.endpoint.player;

import com.github.florianehmke.qompelo.domain.player.Player;
import com.github.florianehmke.qompelo.rest.endpoint.player.mapper.PlayerMapper;
import com.github.florianehmke.qompelo.rest.endpoint.player.model.PlayerCreateRequest;
import com.github.florianehmke.qompelo.rest.endpoint.player.model.PlayerLoginRequest;
import com.github.florianehmke.qompelo.rest.endpoint.player.model.PlayerResponse;
import com.github.florianehmke.qompelo.rest.token.TokenResponse;
import com.github.florianehmke.qompelo.rest.token.TokenService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("players")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

  @Inject TokenService tokenService;
  @Inject PlayerMapper mapper;

  @POST
  @Transactional
  public PlayerResponse create(@Valid PlayerCreateRequest request) {
    return mapper.map(Player.create(request.getName(), request.getPassword()));
  }

  @POST
  @Path("login")
  public TokenResponse login(@Valid PlayerLoginRequest request) {
    var player = Player.loadAndVerifyPassword(request.getName(), request.getPassword());
    var accessToken = tokenService.createToken(player.name);
    return TokenResponse.builder().token(accessToken).build();
  }
}
