package com.github.florianehmke.qompelo.domain;

import io.vertx.mutiny.core.eventbus.EventBus;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.github.florianehmke.util.CollectionUtils.addAndReturn;
import static com.github.florianehmke.util.PanacheUtils.persistAndReturn;

@Entity
@NoArgsConstructor
public class Match extends BaseEntity {

  public ZonedDateTime date;

  @ManyToOne(fetch = FetchType.EAGER)
  public Game game;

  @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
  public Set<Team> teams;

  public static Match create(ZonedDateTime date, Game game, Collection<TeamParameter> teams) {
    var match = persistAndReturn(new Match(date, game));
    teams.forEach(team -> match.addTeam(team.players(), team.getScore()));
    match.score();
    return match;
  }

  public Match(ZonedDateTime date, Game game) {
    this.date = date;
    this.game = game;
    this.teams = new HashSet<>();
  }

  public Team addTeam(Set<Player> players, Integer score) {
    return addAndReturn(teams, Team.create(this, score, players));
  }

  public void score() {
    var highScore = 0;
    var highScoreCount = 0;

    for (Team team : teams) {
      if (team.score > highScore) {
        highScore = team.score;
        highScoreCount = 1;
      } else if (team.score == highScore) {
        highScoreCount++;
      }
    }

    if (highScoreCount < teams.size()) {
      for (Team team : teams) {
        if (team.score == highScore) {
          team.result = ResultEnum.WIN;
        } else {
          team.result = ResultEnum.LOSS;
        }
      }
    } else {
      for (Team team : teams) {
        team.result = ResultEnum.DRAW;
      }
    }
  }

  @PostPersist
  public void postPersist() {
    var eventBus = CDI.current().select(EventBus.class).get();
    eventBus.publish(Event.MATCH_CREATED, this);
  }
}
