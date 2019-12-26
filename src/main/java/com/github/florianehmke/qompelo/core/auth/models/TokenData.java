package com.github.florianehmke.qompelo.core.auth.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenData {

  public interface Claims {
    String PROJECT_ID = "projectId";
  }

  private final Long projectId;

  public Map<String, Object> claims() {
    var claims = new HashMap<String, Object>();
    claims.put(Claims.PROJECT_ID, projectId);
    return claims;
  }
}
