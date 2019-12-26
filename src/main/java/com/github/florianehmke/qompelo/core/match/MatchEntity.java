package com.github.florianehmke.qompelo.core.match;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "MATCHES")
class MatchEntity extends PanacheEntity {

  public ZonedDateTime date;
  public Long gameId;
}
