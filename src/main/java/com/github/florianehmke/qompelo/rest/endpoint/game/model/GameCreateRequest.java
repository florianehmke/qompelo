package com.github.florianehmke.qompelo.rest.endpoint.game.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GameCreateRequest {

  @NotBlank private final String name;
}
