package com.github.florianehmke.qompelo.rest.endpoint.game.mapper;

import com.github.florianehmke.qompelo.domain.game.Game;
import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.game.model.GameResponse;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class)
public interface GameMapper {

  GameResponse map(Game entity);

  Collection<GameResponse> map(Collection<Game> entities);
}
