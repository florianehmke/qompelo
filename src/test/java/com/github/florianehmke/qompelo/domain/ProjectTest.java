package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.game.Game;
import com.github.florianehmke.qompelo.domain.match.Match;
import com.github.florianehmke.qompelo.domain.match.ResultEnum;
import com.github.florianehmke.qompelo.domain.match.Team;
import com.github.florianehmke.qompelo.domain.match.TeamParameter;
import com.github.florianehmke.qompelo.domain.player.Player;
import com.github.florianehmke.qompelo.domain.project.Project;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class ProjectTest {

  private static final String TEST_PROJECT = "Test Project";

  @Test
  public void test() {
    persist();
    verify();
  }

  @Transactional(Transactional.TxType.NOT_SUPPORTED)
  public void verify() {
    Project project = Project.find("name", TEST_PROJECT).singleResult();
    assertThat(project).isNotNull();
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
    var project = Project.create(TEST_PROJECT, "secret");
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
