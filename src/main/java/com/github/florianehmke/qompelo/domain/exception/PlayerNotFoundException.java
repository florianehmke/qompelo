package com.github.florianehmke.qompelo.domain.exception;

public class PlayerNotFoundException extends CompeloException {

  public PlayerNotFoundException() {
    super("user not found");
  }
}
