package com.github.florianehmke.qompelo.core.project;

import com.github.florianehmke.qompelo.core.project.models.ProjectCreateRequest;

public interface ProjectTestData {

  ProjectCreateRequest PROJECT_CREATE_REQUEST = new ProjectCreateRequest("Test Project", "secret");
}
