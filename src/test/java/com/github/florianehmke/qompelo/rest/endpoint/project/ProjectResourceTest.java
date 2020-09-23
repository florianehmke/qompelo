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

import static com.github.florianehmke.qompelo.testing.util.Random.EASY_RANDOM;
import static com.github.florianehmke.qompelo.testing.util.RestAssured.givenTestUser;
import static com.github.florianehmke.qompelo.testing.util.Transaction.doInTransaction;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestHTTPEndpoint(value = ProjectResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectResourceTest {

  @Inject UserTransaction transaction;

  @Test
  public void testCreateProject() {
    var body = EASY_RANDOM.nextObject(ProjectCreateRequest.class);
    var spec = givenTestUser(transaction).body(body).post();

    doInTransaction(transaction, () -> spec.then().statusCode(200));

    Project persisted = Project.find("name", body.getName()).singleResult();
    assertThat(persisted).isNotNull();
  }

  @Test
  public void testMine() {
    var projectName = EASY_RANDOM.nextObject(String.class);
    var userName = EASY_RANDOM.nextObject(String.class);

    doInTransaction(
        transaction,
        () -> {
          var testProject = Project.create(projectName, projectName);
          var testUser = Player.create(userName, userName);
          testUser.addProject(testProject.id, projectName);
        });

    var spec = givenTestUser(userName).get("mine");
    var responseList =
        spec.then().statusCode(200).extract().jsonPath().getList(".", ProjectResponse.class);

    assertThat(responseList).extracting(ProjectResponse::getName).containsExactly(projectName);
  }

  @Test
  public void testListAll() {
    var projectName = EASY_RANDOM.nextObject(String.class);

    doInTransaction(transaction, () -> Project.create(projectName, projectName));

    var spec = givenTestUser(transaction).get();
    var responseList =
        spec.then().statusCode(200).extract().jsonPath().getList(".", ProjectResponse.class);

    assertThat(responseList).hasSizeGreaterThanOrEqualTo(1);
    assertThat(responseList).extracting(ProjectResponse::getName).contains(projectName);
  }
}
