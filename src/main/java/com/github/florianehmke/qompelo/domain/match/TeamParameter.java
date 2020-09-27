package com.github.florianehmke.qompelo.domain.match;

import com.github.florianehmke.qompelo.domain.player.Player;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

import static java.util.Objects.requireNonNull;

@Value
@Builder
public class TeamParameter {
  private final Set<Long> playerIds;
  private final Integer score;

  public Set<Player> players() {
    return Player.findByIds(requireNonNull(playerIds));
  }
}
