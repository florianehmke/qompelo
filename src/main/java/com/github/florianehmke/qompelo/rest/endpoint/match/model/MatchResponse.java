package com.github.florianehmke.qompelo.rest.endpoint.match.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchResponse {

  private final Long id;
  private final Collection<TeamResponse> teams;
}
