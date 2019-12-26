package com.github.florianehmke.qompelo.core.player;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYERS")
class PlayerEntity extends PanacheEntity {

  public String name;
  public Long projectId;
}
