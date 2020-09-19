package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.exception.PlayerNotFoundException;
import com.github.florianehmke.qompelo.domain.exception.PlayerPasswordWrongException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.florianehmke.qompelo.util.PasswordUtils.hashPassword;
import static com.github.florianehmke.qompelo.util.PasswordUtils.verifyPassword;

@Entity
@NoArgsConstructor
public class Player extends PanacheEntity {

  public String name;

  private String passwordHash;

  @JoinTable
  @ManyToMany(fetch = FetchType.EAGER)
  public Set<Project> projects;

  public Player(String name, String password) {
    this.name = name;
    this.passwordHash = hashPassword(password);
    this.projects = new HashSet<>();
  }

  public static Player create(String name, String password) {
    var player = new Player(name, password);
    player.persist();
    return player;
  }

  public static Player mustLoad(String name) {
    try {
      return Player.find("name", name).singleResult();
    } catch (Exception e) {
      throw new PlayerNotFoundException();
    }
  }

  public static Player loadAndVerifyPassword(String name, String password) {
    var player = mustLoad(name);
    var authorized = verifyPassword(password, player.passwordHash);
    if (!authorized) {
      throw new PlayerPasswordWrongException();
    }
    return player;
  }

  public static Set<Player> findByIds(Set<Long> ids) {
    return new HashSet<>(Player.list("id in ?1", ids));
  }

  public Project addProject(Long projectId, String password) {
    Project project = Project.loadAndVerifyPassword(projectId, password);
    projects.add(project);
    project.players.add(this);
    return project;
  }

  public List<Long> projectIds() {
    return projects.stream().map(p -> p.id).collect(Collectors.toList());
  }
}
