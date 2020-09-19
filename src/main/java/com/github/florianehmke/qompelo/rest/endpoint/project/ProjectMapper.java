package com.github.florianehmke.qompelo.rest.endpoint.project;

import com.github.florianehmke.qompelo.rest.endpoint.CentralMapperConfig;
import com.github.florianehmke.qompelo.rest.endpoint.project.models.ProjectResponse;
import com.github.florianehmke.qompelo.domain.Project;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(config = CentralMapperConfig.class)
interface ProjectMapper {

  ProjectResponse map(Project entity);

  Collection<ProjectResponse> map(Collection<Project> entities);
}
