package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.exception.PlayerNotFoundException;
import com.github.florianehmke.qompelo.domain.exception.PlayerPasswordWrongException;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.florianehmke.qompelo.util.CollectionUtils.addAndReturn;
import static com.github.florianehmke.qompelo.util.JpaUtils.persistAndReturn;
import static com.github.florianehmke.qompelo.util.PasswordUtils.hashPassword;
import static com.github.florianehmke.qompelo.util.PasswordUtils.verifyPassword;

@Entity
@NoArgsConstructor
public class Player extends BaseEntity {

  public String name;
  public String passwordHash;

  @JoinTable
  @ManyToMany(fetch = FetchType.EAGER)
  public Set<Project> projects;

  public static Player create(String name, String password) {
    return persistAndReturn(new Player(name, password));
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

  public Player(String name, String password) {
    this.name = name;
    this.passwordHash = hashPassword(password);
    this.projects = new HashSet<>();
  }

  public Project addProject(Long projectId, String password) {
    return addAndReturn(projects, Project.loadAndVerifyPassword(projectId, password));
  }

  public List<Long> projectIds() {
    return projects.stream().map(p -> p.id).collect(Collectors.toList());
  }
}
