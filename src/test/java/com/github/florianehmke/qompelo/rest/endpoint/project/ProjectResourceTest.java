package com.github.florianehmke.qompelo.rest.endpoint.project;

import com.github.florianehmke.qompelo.TestUtils;
import com.github.florianehmke.qompelo.domain.Player;
import com.github.florianehmke.qompelo.domain.Project;
import com.github.florianehmke.qompelo.rest.endpoint.project.model.ProjectCreateRequest;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.transaction.Transactional;

import static com.github.florianehmke.qompelo.TestUtils.easyRandom;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(value = ProjectResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectResourceTest {

  EasyRandom easyRandom;

  Player testUser;
  Project testProject;

  @BeforeAll
  @Transactional
  public void setUp() {
    easyRandom = easyRandom();

    testProject = Project.create("TEST-PROJECT", "secret");
    testUser = Player.create("TEST-USER", "secret");
    testUser.addProject(testProject.id, "secret");
  }

  @Test
  @Transactional
  public void testCreateProject() {
    var request = easyRandom.nextObject(ProjectCreateRequest.class);
    with()
        .contentType(JSON)
        .body(request)
        .header("Authorization", "Bearer " + TestUtils.accessToken())
        .when()
        .post()
        .then()
        .statusCode(200);

    Project persisted = Project.find("name", request.getName()).singleResult();
    assertThat(persisted).isNotNull();
  }

  @Test
  @Transactional
  public void testMine() {
    // TODO
  }
}
