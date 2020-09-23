package com.github.florianehmke.qompelo.rest.endpoint.project;

import com.github.florianehmke.qompelo.domain.Player;
import com.github.florianehmke.qompelo.domain.Project;
import com.github.florianehmke.qompelo.rest.endpoint.project.model.ProjectCreateRequest;
import com.github.florianehmke.qompelo.rest.endpoint.project.model.ProjectResponse;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import static com.github.florianehmke.qompelo.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(value = ProjectResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectResourceTest {

  @Inject UserTransaction transaction;

  @Test
  public void testCreateProject() {
    var body = EASY_RANDOM.nextObject(ProjectCreateRequest.class);
    var spec = givenTestUser(transaction);

    doInTransaction(transaction, () -> spec.body(body).post().then().statusCode(200));

    Project persisted = Project.find("name", body.getName()).singleResult();
    assertThat(persisted).isNotNull();
  }

  @Test
  public void testMine() {
    final String projectName = EASY_RANDOM.nextObject(String.class);
    final String userName = EASY_RANDOM.nextObject(String.class);

    doInTransaction(
        transaction,
        () -> {
          var testProject = Project.create(projectName, projectName);
          var testUser = Player.create(userName, userName);
          testUser.addProject(testProject.id, projectName);
        });

    var responseList =
        givenTestUser(userName)
            .get("mine")
            .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getList(".", ProjectResponse.class);

    assertThat(responseList).extracting(ProjectResponse::getName).containsExactly(projectName);
  }

  @Test
  public void testListAll() {
    var projectName = EASY_RANDOM.nextObject(String.class);

    doInTransaction(transaction, () -> Project.create(projectName, projectName));

    var responseList =
        givenTestUser(transaction)
            .get()
            .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getList(".", ProjectResponse.class);

    assertThat(responseList).hasSizeGreaterThanOrEqualTo(1);
    assertThat(responseList).extracting(ProjectResponse::getName).contains(projectName);
  }
}
