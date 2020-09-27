package com.github.florianehmke.qompelo.domain;

import com.github.florianehmke.qompelo.domain.events.MatchCreated;
import com.github.florianehmke.qompelo.domain.events.RatingUpdate;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static javax.enterprise.event.TransactionPhase.AFTER_SUCCESS;
import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;
import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@Slf4j
@ApplicationScoped
public class RatingService {

  @Inject ManagedExecutor executor;
  @Inject @RatingUpdate
  Event<Match> ratingUpdate;

  @Transactional(NOT_SUPPORTED)
  public void onMatchCreated(@Observes(during = AFTER_SUCCESS) @MatchCreated Match match) {
    ratingUpdate.fireAsync(match, NotificationOptions.ofExecutor(executor));
  }

  @Transactional(REQUIRES_NEW)
  public void onRatingUpdate(@ObservesAsync @RatingUpdate Match match) {
    try {
      log.info("processing rating update due to match with id {}", match.id);
      var eloMatch = EloMatch.create(match);
      log.info("created elo match with id {}", eloMatch.id);
      log.info("update ratings...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
