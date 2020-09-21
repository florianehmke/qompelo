package com.github.florianehmke.qompelo.rest.endpoint.match.mapper;

import com.github.florianehmke.qompelo.domain.TeamParameter;
import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.match.model.MatchCreateRequest;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class)
public interface TeamParameterMapper {

  TeamParameter map(MatchCreateRequest.TeamParameter parameter);

  Collection<TeamParameter> map(Collection<MatchCreateRequest.TeamParameter> parameters);
}
