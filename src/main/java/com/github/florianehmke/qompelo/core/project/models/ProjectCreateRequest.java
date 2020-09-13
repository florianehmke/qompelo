package com.github.florianehmke.qompelo.core.project.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ProjectCreateRequest {

  @NotBlank public String name;
  @NotBlank public String password;
}
