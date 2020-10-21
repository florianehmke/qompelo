package com.github.florianehmke.rating;

import java.util.ArrayList;
import java.util.List;

public class RatedMatch {

  private List<RatedPlayer> players = new ArrayList<>();

  public void addPlayer(Long id, Integer place, Integer rating) {
    players.add(new RatedPlayer(id, place, rating));
  }

  public RatedPlayer getPlayerById(long id) {
    for (RatedPlayer player : players) {
      if (id == player.getId()) {
        return player;
      }
    }
    return null;
  }

  public void calculate() {
    var n = players.size();
    var k = 32 / (double) (n - 1);

    for (int i = 0; i < n; i++) {
      var place = players.get(i).getPlace();
      var rating = players.get(i).getRatingPre();

      for (int j = 0; j < n; j++) {
        if (i == j) {
          continue;
        }

        var opponentPlace = players.get(j).getPlace();
        var opponentRating = players.get(j).getRatingPre();

        var s = 0.5;
        if (place < opponentPlace) {
          s = 1.0;
        }
        if (place > opponentPlace) {
          s = 0.0;
        }

        var ea = 1 / (1.0 + Math.pow(10.0, (opponentRating - rating) / 400.0));
        players.get(i).addDelta((int) Math.round(k * (s - ea)));
      }

      players.get(i).updateRatingPost();
    }
  }
}
