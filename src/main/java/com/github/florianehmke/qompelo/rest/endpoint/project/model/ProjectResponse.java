package com.github.florianehmke.qompelo.rest.endpoint.project.model;

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
