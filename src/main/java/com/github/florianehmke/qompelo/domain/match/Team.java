package com.github.florianehmke.qompelo.domain.match;

import com.github.florianehmke.qompelo.domain.BaseEntity;
import com.github.florianehmke.qompelo.domain.player.Player;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.github.florianehmke.util.PanacheUtils.persistAndReturn;

@Entity
@NoArgsConstructor
public class Team extends BaseEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  public Match match;

  @JoinTable
  @ManyToMany(fetch = FetchType.EAGER)
  public Set<Player> players;

  public Integer score;

  @Enumerated(EnumType.STRING)
  public ResultEnum result;

  public static Team create(Match match, Integer score, Collection<Player> players) {
    return persistAndReturn(new Team(match, score, players));
  }

  public Team(Match match, Integer score, Collection<Player> players) {
    this.match = match;
    this.score = score;
    this.players = new HashSet<>(players);
  }
}
