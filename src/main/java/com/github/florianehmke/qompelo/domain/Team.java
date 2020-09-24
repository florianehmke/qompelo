package com.github.florianehmke.qompelo.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

  public Team(Match match, Integer score) {
    this.match = match;
    this.score = score;
    this.players = new HashSet<>();
  }

  public static Team create(Match match, Integer score) {
    var team = new Team(match, score);
    team.persist();
    match.teams.add(team);
    return team;
  }
}
