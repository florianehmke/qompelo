package com.github.florianehmke.qompelo.domain.player;

import com.github.florianehmke.qompelo.domain.CompeloException;

public class PlayerNotFoundException extends CompeloException {

  public PlayerNotFoundException() {
    super("user not found");
  }
}
