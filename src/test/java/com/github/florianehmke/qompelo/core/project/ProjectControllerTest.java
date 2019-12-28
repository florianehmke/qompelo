package com.github.florianehmke.qompelo.core.project;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.github.florianehmke.qompelo.core.project.ProjectTestData.PROJECT_CREATE_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class ProjectControllerTest {

  @Inject ProjectController controller;

  @Test
  void createProject() {
    var createdProject = controller.createProject(PROJECT_CREATE_REQUEST);

    assertThat(createdProject).isNotNull();
    assertThat(createdProject.getId()).isEqualTo(1L);
    assertThat(createdProject.getName()).isEqualTo(PROJECT_CREATE_REQUEST.getName());
  }
}
