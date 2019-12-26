package com.github.florianehmke.qompelo.core.rating;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RATINGS")
class RatingEntity extends PanacheEntity {}
