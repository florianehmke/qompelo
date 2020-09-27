package com.github.florianehmke.qompelo.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity extends PanacheEntity {

  public String createdBy;
  public LocalDateTime createdAt;

  public String updatedBy;
  public LocalDateTime updatedAt;
}
