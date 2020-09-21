package com.github.florianehmke.qompelo.rest.endpoint.game;

import com.github.florianehmke.qompelo.domain.Game;

public class GameSubResource {

  protected Game game;

  public GameSubResource withGame(Game game) {
    this.game = game;
    return this;
  }
}
