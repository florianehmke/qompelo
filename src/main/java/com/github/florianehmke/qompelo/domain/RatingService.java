package com.github.florianehmke.qompelo.domain;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import static javax.enterprise.event.TransactionPhase.AFTER_SUCCESS;
import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@Slf4j
@ApplicationScoped
public class RatingService {

  @Transactional(REQUIRES_NEW)
  public void onMatchCreated(@Observes(during = AFTER_SUCCESS) @MatchCreated Match match) {
    log.info("processing match with id {}", match.id);
    var eloMatch = EloMatch.create(match);
    log.info("created elo match with id {}", eloMatch.id);
  }
}
