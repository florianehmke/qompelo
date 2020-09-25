package com.github.florianehmke.qompelo.util;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class JpaUtils {

  public static <T extends PanacheEntity> T persistAndReturn(T entity) {
    entity.persist();
    return entity;
  }
}
