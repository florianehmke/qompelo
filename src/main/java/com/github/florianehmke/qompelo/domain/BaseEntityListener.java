package com.github.florianehmke.qompelo.domain;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class BaseEntityListener {

  public static final String SYSTEM_USER = "SYSTEM";

  @PrePersist
  public void prePersist(BaseEntity entity) {
    entity.createdAt = LocalDateTime.now();
    entity.updatedAt = LocalDateTime.now();

    var currentUser = currentUser();
    entity.createdBy = currentUser;
    entity.updatedBy = currentUser;
  }

  @PreUpdate
  public void preUpdate(BaseEntity entity) {
    entity.updatedAt = LocalDateTime.now();
    entity.updatedBy = currentUser();
  }

  private static String currentUser() {
    // FIXME: https://github.com/quarkusio/quarkus/issues/6948
    var context = CDI.current().select(JsonWebToken.class).get();
    if (context != null) {
      String userName = context.getClaim(Claims.preferred_username.name());
      if (userName != null) {
        return userName;
      }
    }
    return SYSTEM_USER;
  }
}
