package com.github.florianehmke.qompelo.rest.endpoint.match.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
public class MatchCreateRequest {

  @Size(min = 2, max = 20)
  private final List<@Valid TeamParameter> teams;

  @Data
  public static class TeamParameter {

    @NotEmpty private final Set<Long> playerIds;
    @NotNull private final Integer score;
  }
}
