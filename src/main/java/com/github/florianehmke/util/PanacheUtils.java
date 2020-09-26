package com.github.florianehmke.util;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class PanacheUtils {

  public static <T extends PanacheEntity> T persistAndReturn(T entity) {
    entity.persist();
    return entity;
  }
}
