package com.github.florianehmke.qompelo.rest.endpoint.match.mapper;

import com.github.florianehmke.qompelo.domain.Team;
import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.match.model.TeamResponse;
import com.github.florianehmke.qompelo.rest.endpoint.player.mapper.PlayerMapper;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class, uses = PlayerMapper.class)
public interface TeamMapper {

  TeamResponse map(Team entity);

  Collection<TeamResponse> map(Collection<Team> entities);
}
