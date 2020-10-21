package com.github.florianehmke.qompelo.rest.token;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Data;

@Data
@ConfigProperties(prefix = "auth.jwt")
public class TokenConfiguration {

  private String issuer;
  private Long maxAge;
}
