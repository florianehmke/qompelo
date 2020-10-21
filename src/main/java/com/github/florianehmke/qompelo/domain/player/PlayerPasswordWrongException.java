package com.github.florianehmke.qompelo.domain.player;

import com.github.florianehmke.qompelo.domain.CompeloException;

public class PlayerPasswordWrongException extends CompeloException {

  public PlayerPasswordWrongException() {
    super("invalid password");
  }
}
