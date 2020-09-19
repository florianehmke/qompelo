package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.exception.ProjectNotFoundException;
import com.github.florianehmke.qompelo.domain.exception.ProjectPasswordWrongException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.github.florianehmke.qompelo.util.PasswordUtils.hashPassword;
import static com.github.florianehmke.qompelo.util.PasswordUtils.verifyPassword;

@Entity
@NoArgsConstructor
public class Project extends PanacheEntity {

  @Column(unique = true)
  public String name;

  public String passwordHash;

  @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
  public Set<Player> players;

  @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
  public Set<Game> games;

  public Project(String name, String password) {
    this.name = name;
    this.passwordHash = hashPassword(password);
    this.players = new HashSet<>();
    this.games = new HashSet<>();
  }

  public static Project create(String name, String password) {
    var project = new Project(name, password);
    project.persist();
    return project;
  }

  public static Project mustLoad(Long projectId) {
    Project project = Project.findById(projectId);
    if (project == null) {
      throw new ProjectNotFoundException();
    }
    return project;
  }

  public static Project loadAndVerifyPassword(Long projectId, String password) {
    var project = mustLoad(projectId);
    var authorized = verifyPassword(password, project.passwordHash);
    if (!authorized) {
      throw new ProjectPasswordWrongException();
    }
    return project;
  }

  public Game addGame(String name) {
    return Game.create(name, this);
  }
}
