package com.github.florianehmke.qompelo.domain.exception;

public class GameNotFoundException extends CompeloException {

  public GameNotFoundException() {
    super("game not found");
  }
}
