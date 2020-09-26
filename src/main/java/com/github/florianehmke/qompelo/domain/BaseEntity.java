package com.github.florianehmke.qompelo.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity extends PanacheEntity {

  public static final String SYSTEM_USER = "SYSTEM";

  public String createdBy;
  public LocalDateTime createdAt;

  public String updatedBy;
  public LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();

    var currentUser = currentUser();
    createdBy = currentUser;
    updatedBy = currentUser;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
    updatedBy = currentUser();
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
