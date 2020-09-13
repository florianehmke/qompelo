package com.github.florianehmke.qompelo.core.auth.models;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class AuthRequest {

  @NotNull private final Long projectId;
  @NotBlank private final String password;
}
