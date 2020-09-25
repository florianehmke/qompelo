package com.github.florianehmke.qompelo.rest.endpoint.project.mapper;

import com.github.florianehmke.qompelo.domain.Project;
import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.project.model.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class)
public interface ProjectMapper {

  ProjectResponse map(Project entity);

  Collection<ProjectResponse> map(Collection<Project> entities);
}
