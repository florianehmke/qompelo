package com.github.florianehmke.qompelo.core.game;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
class GameEntity extends PanacheEntity {

  public String name;
  public Long projectId;
}
