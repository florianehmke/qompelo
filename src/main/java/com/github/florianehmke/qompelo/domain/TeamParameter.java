package com.github.florianehmke.qompelo.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class TeamParameter {
    private final Set<Long> playerIds;
    private final Integer score;
}
