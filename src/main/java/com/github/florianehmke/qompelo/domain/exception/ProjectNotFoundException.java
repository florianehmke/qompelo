package com.github.florianehmke.qompelo.domain.exception;

public class ProjectNotFoundException extends CompeloException {

  public ProjectNotFoundException() {
    super("project not found");
  }
}
