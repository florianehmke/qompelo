package com.github.florianehmke.qompelo.domain.exception;

public class PlayerPasswordWrongException extends CompeloException {

  public PlayerPasswordWrongException() {
    super("invalid password");
  }
}
