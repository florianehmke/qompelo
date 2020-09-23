package com.github.florianehmke.qompelo.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Match extends PanacheEntity {

  public ZonedDateTime date;

  @ManyToOne(fetch = FetchType.EAGER)
  public Game game;

  @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
  public Set<Team> teams;

  public Match(ZonedDateTime date, Game game) {
    this.date = date;
    this.game = game;
    this.teams = new HashSet<>();
  }

  public static Match create(ZonedDateTime date, Game game, Collection<TeamParameter> teams) {
    var match = new Match(date, game);
    match.persist();
    game.matches.add(match);

    for (TeamParameter team : teams) {
      var players = Player.findByIds(team.getPlayerIds());
      match.addTeam(players, team.getScore());
    }

    match.score();
    return match;
  }

  public Team addTeam(Set<Player> players, Integer score) {
    var team = Team.create(this, score);
    team.players.addAll(players);
    return team;
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
}
