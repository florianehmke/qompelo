package com.github.florianehmke.qompelo.core.project;

import com.github.florianehmke.qompelo.core.project.models.ProjectResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(injectionStrategy = InjectionStrategy.FIELD, componentModel = "jsr330")
interface ProjectMapper {

  ProjectResponse map(ProjectEntity entity);

  Collection<ProjectResponse> map(Collection<ProjectEntity> entities);
}
