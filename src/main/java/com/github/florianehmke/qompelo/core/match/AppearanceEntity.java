package com.github.florianehmke.qompelo.core.match;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APPEARANCES")
class AppearanceEntity extends PanacheEntity {

  public Long matchId;
  public Long teamId;
  public Long playerId;
  public Integer ratingDelta;
}
