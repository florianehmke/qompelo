package com.github.florianehmke.qompelo.core.auth.configuration;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Data;

@Data
@ConfigProperties(prefix = "auth.jwt")
public class JwtConfiguration {

  private String issuer;
  private Long maxAge;
}
