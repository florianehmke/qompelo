package com.github.florianehmke.qompelo.domain;

import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class RatingService {

  @ConsumeEvent(value = Event.MATCH_CREATED)
  public void onMatchCreated(Match match) {
    log.info("processing match with id {}", match.id);
  }
}
