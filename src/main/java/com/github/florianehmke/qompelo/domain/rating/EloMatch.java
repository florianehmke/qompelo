package com.github.florianehmke.qompelo.domain.rating;

import com.github.florianehmke.qompelo.domain.BaseEntity;
import com.github.florianehmke.qompelo.domain.match.Match;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import static com.github.florianehmke.util.PanacheUtils.persistAndReturn;

@Entity
@NoArgsConstructor
public class EloMatch extends BaseEntity {

  @OneToOne public Match match;

  @Enumerated(value = EnumType.STRING)
  public EloMatchState state;

  public static EloMatch create(Match match) {
    return persistAndReturn(new EloMatch(match));
  }

  public EloMatch(Match match) {
    this.match = match;
    this.state = EloMatchState.NEW;
  }
}
