package com.github.florianehmke.qompelo.rest.endpoint.player.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PlayerCreateRequest {

  @NotBlank private final String name;
  @NotBlank private final String password;
}
