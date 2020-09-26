package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.exception.ProjectNotFoundException;
import com.github.florianehmke.qompelo.domain.exception.ProjectPasswordWrongException;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.github.florianehmke.util.CollectionUtils.addAndReturn;
import static com.github.florianehmke.util.PanacheUtils.persistAndReturn;
import static com.github.florianehmke.util.PasswordUtils.hashPassword;
import static com.github.florianehmke.util.PasswordUtils.verifyPassword;
import static java.util.Optional.ofNullable;

@Entity
@NoArgsConstructor
public class Project extends BaseEntity {

  @Column(unique = true)
  public String name;

  public String passwordHash;

  @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
  public Set<Player> players;

  @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
  public Set<Game> games;

  public static Project create(String name, String password) {
    return persistAndReturn(new Project(name, password));
  }

  public static Project mustLoad(Long projectId) {
    return (Project) ofNullable(findById(projectId)).orElseThrow(ProjectNotFoundException::new);
  }

  public static Project loadAndVerifyPassword(Long projectId, String password) {
    var project = mustLoad(projectId);
    var authorized = verifyPassword(password, project.passwordHash);
    if (!authorized) {
      throw new ProjectPasswordWrongException();
    }
    return project;
  }

  public Project(String name, String password) {
    this.name = name;
    this.passwordHash = hashPassword(password);
    this.players = new HashSet<>();
    this.games = new HashSet<>();
  }

  public Game addGame(String name) {
    return addAndReturn(games, Game.create(name, this));
  }
}
