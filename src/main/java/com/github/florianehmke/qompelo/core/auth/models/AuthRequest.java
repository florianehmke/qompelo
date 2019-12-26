package com.github.florianehmke.qompelo.core.auth.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {

  @NotNull private Long projectId;
  @NotBlank private String password;
}
