package com.github.florianehmke.qompelo.rest.endpoint.player;

import com.github.florianehmke.qompelo.domain.Player;
import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.player.models.PlayerResponse;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class)
public interface PlayerMapper {

  PlayerResponse map(Player entity);

  Collection<PlayerResponse> map(Collection<Player> entities);
}