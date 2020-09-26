package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.exception.GameNotFoundException;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.github.florianehmke.util.CollectionUtils.addAndReturn;
import static com.github.florianehmke.util.PanacheUtils.persistAndReturn;
import static java.time.ZonedDateTime.now;
import static java.util.Optional.ofNullable;

@Entity
@NoArgsConstructor
public class Game extends BaseEntity {

  public String name;

  @ManyToOne(fetch = FetchType.EAGER)
  public Project project;

  @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
  public Set<Match> matches;

  public static Game create(String name, Project project) {
    return persistAndReturn(new Game(name, project));
  }

  public static Game mustLoad(Long gameId) {
    return (Game) ofNullable(findById(gameId)).orElseThrow(GameNotFoundException::new);
  }

  public Game(String name, Project project) {
    this.name = name;
    this.project = project;
    this.matches = new HashSet<>();
  }

  public Match addMatch(Collection<TeamParameter> teams) {
    return addAndReturn(matches, Match.create(now(), this, teams));
  }
}
