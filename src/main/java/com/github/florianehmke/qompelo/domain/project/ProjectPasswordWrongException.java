package com.github.florianehmke.qompelo.domain.project;

import com.github.florianehmke.qompelo.domain.CompeloException;

public class ProjectPasswordWrongException extends CompeloException {

  public ProjectPasswordWrongException() {
    super("invalid password");
  }
}
