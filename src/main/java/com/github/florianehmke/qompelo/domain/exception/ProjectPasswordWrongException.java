package com.github.florianehmke.qompelo.domain.exception;

public class ProjectPasswordWrongException extends CompeloException {

  public ProjectPasswordWrongException() {
    super("invalid password");
  }
}
