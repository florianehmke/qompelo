package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.exception.GameNotFoundException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.ZonedDateTime.now;

@Entity
@NoArgsConstructor
public class Game extends PanacheEntity {

  public String name;

  @ManyToOne(fetch = FetchType.EAGER)
  public Project project;

  @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
  public Set<Match> matches;

  public Game(String name, Project project) {
    this.name = name;
    this.project = project;
    this.matches = new HashSet<>();
  }

  public static Game create(String name, Project project) {
    var game = new Game(name, project);
    game.persist();
    project.games.add(game);
    return game;
  }

  public static Game mustLoad(Long gameId) {
    Game game = Game.findById(gameId);
    if (game == null) {
      throw new GameNotFoundException();
    }
    return game;
  }

  public Match addMatch(List<TeamParameter> teams) {
    var match = Match.create(now(), this);

    for (TeamParameter team : teams) {
      var players = Player.findByIds(team.getPlayerIds());
      match.addTeam(players, team.getScore());
    }

    match.score();
    return match;
  }
}
