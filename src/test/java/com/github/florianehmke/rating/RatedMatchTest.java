package com.github.florianehmke.rating;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RatedMatchTest {

  @Test
  public void foo() {
    var match = new RatedMatch();
    match.addPlayer(1L, 1, 1500);
    match.addPlayer(2L, 2, 1500);
    match.calculate();

    assertThat(match.getPlayerById(1L)).extracting(RatedPlayer::getRatingPost).isEqualTo(1516);
    assertThat(match.getPlayerById(2L)).extracting(RatedPlayer::getRatingPost).isEqualTo(1484);
  }
}
