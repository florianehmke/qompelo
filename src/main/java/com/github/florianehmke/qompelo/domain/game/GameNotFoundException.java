package com.github.florianehmke.qompelo.domain.game;

import com.github.florianehmke.qompelo.domain.CompeloException;

public class GameNotFoundException extends CompeloException {

  public GameNotFoundException() {
    super("game not found");
  }
}
