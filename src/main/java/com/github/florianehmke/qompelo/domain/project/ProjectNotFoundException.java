package com.github.florianehmke.qompelo.domain.project;

import com.github.florianehmke.qompelo.domain.CompeloException;

public class ProjectNotFoundException extends CompeloException {

  public ProjectNotFoundException() {
    super("project not found");
  }
}
