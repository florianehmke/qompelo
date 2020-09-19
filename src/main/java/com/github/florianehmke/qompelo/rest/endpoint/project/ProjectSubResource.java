package com.github.florianehmke.qompelo.rest.endpoint.project;

import com.github.florianehmke.qompelo.domain.Project;

public class ProjectSubResource {

  protected Project project;

  public ProjectSubResource withProject(Project project) {
    this.project = project;
    return this;
  }
}
