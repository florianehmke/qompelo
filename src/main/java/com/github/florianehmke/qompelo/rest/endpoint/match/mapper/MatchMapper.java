package com.github.florianehmke.qompelo.rest.endpoint.match.mapper;

import com.github.florianehmke.qompelo.domain.match.Match;
import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.match.model.MatchResponse;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class, uses = TeamMapper.class)
public interface MatchMapper {

  MatchResponse map(Match entity);

  Collection<MatchResponse> map(Collection<Match> entities);
}
