package com.github.florianehmke.qompelo.core.project.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectCreateRequest {

  @NotBlank private final String name;
  @NotBlank private final String password;
}
