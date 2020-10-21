package com.github.florianehmke.qompelo;

import com.github.florianehmke.qompelo.domain.game.Game;
import com.github.florianehmke.qompelo.domain.match.TeamParameter;
import com.github.florianehmke.qompelo.domain.player.Player;
import com.github.florianehmke.qompelo.domain.project.Project;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class Filler {

  @Transactional
  void onStart(@Observes StartupEvent ev) {
    if (devMode() && !dbInitialized()) {
      var player1 = Player.create("Player 1", "secret");
      var player2 = Player.create("Player 2", "secret");

      var project = Project.create("Avengers", "secret");
      player1.addProject(project.id, "secret");
      player2.addProject(project.id, "secret");

      Game game = project.addGame("FIFA");
      game.addMatch(
          List.of(
              TeamParameter.builder().playerIds(Set.of(player1.id)).score(1).build(),
              TeamParameter.builder().playerIds(Set.of(player2.id)).score(2).build()));
    }
  }

  private boolean devMode() {
    return "dev".equalsIgnoreCase(ProfileManager.getActiveProfile());
  }

  private boolean dbInitialized() {
    return Project.findAll().count() != 0;
  }
}
