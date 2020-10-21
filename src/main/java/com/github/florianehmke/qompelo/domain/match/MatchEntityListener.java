package com.github.florianehmke.qompelo.domain.match;

import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.persistence.PostPersist;

public class MatchEntityListener {

  @PostPersist
  public void postPersist(Match entity) {
    // FIXME: https://github.com/quarkusio/quarkus/issues/6948
    var qualifier = new AnnotationLiteral<MatchCreated>() {};
    CDI.current().select(Event.class, qualifier).get().fire(entity);
  }
}
