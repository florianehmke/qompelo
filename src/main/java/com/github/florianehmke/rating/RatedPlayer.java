package com.github.florianehmke.rating;

import lombok.Data;

@Data
public class RatedPlayer {

  private final long id;
  private final int place;
  private final int ratingPre;

  private int ratingPost = 0;
  private int ratingDelta = 0;

  RatedPlayer(Long id, Integer place, Integer rating) {
    this.id = id;
    this.place = place;
    this.ratingPre = rating;
  }

  void addDelta(int delta) {
    ratingDelta += delta;
  }

  void updateRatingPost() {
    ratingPost = ratingPre + ratingDelta;
  }
}
