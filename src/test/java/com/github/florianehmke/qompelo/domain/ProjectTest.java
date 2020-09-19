package com.github.florianehmke.qompelo.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class ProjectTest {

  @Test
  public void test() {
    persist();
    verify();
  }

  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public void verify() {
    List<Project> projects = Project.listAll();
    assertThat(projects).hasSize(1);

    Project project = projects.get(0);
    assertThat(project.players).hasSize(2);
    assertThat(project.games).hasSize(1);

    Set<Game> games = project.games;
    Game game = games.iterator().next();
    assertThat(game.matches).hasSize(1);

    Match match = game.matches.iterator().next();
    assertThat(match.teams).hasSize(2);
    assertThat(match.teams)
        .extracting(team -> team.result)
        .containsExactlyInAnyOrder(ResultEnum.LOSS, ResultEnum.WIN);

    for (Team team : match.teams) {
      assertThat(team.players).hasSize(1);
    }
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public void persist() {
    var project = Project.create("Test Project", "secret");
    var player1 = Player.create("Player 1", "secret");
    var player2 = Player.create("Player 2", "secret");

    player1.addProject(project.id, "secret");
    player2.addProject(project.id, "secret");

    Game game = project.addGame("FIFA");
    var t1 = TeamParameter.builder().playerIds(Set.of(player1.id)).score(1).build();
    var t2 = TeamParameter.builder().playerIds(Set.of(player2.id)).score(2).build();

    game.addMatch(List.of(t1, t2));
  }
}
