package com.github.florianehmke.qompelo.rest.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenData {

  public interface Claims {
    String USER_NAME = "userName";
    String PROJECTS = "projects";
  }

  private final String userName;
  private final List<Long> projects;
}
