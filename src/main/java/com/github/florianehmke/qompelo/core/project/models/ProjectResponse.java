package com.github.florianehmke.qompelo.core.project.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectResponse {

  private final Long id;
  private final String name;
}
