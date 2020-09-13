package com.github.florianehmke.qompelo.core.auth.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AuthRequest {

  @NotNull public Long projectId;
  @NotBlank public String password;
}
