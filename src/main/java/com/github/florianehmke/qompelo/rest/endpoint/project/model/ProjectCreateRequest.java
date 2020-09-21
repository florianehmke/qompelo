package com.github.florianehmke.qompelo.rest.endpoint.project.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectCreateRequest {

  @NotBlank private final String name;
  @NotBlank private final String password;
}
