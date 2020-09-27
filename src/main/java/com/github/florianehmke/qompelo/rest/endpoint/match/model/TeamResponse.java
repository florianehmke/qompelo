package com.github.florianehmke.qompelo.rest.endpoint.match.model;

import com.github.florianehmke.qompelo.domain.match.ResultEnum;
import com.github.florianehmke.qompelo.rest.endpoint.player.model.PlayerResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamResponse {

  private final Long id;
  private final Integer score;
  private final ResultEnum result;
  private final Collection<PlayerResponse> players;
}
