package com.github.florianehmke.qompelo.core.project;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECTS")
class ProjectEntity extends PanacheEntity {

  @Column(unique = true)
  public String name;

  public String passwordHash;
}
