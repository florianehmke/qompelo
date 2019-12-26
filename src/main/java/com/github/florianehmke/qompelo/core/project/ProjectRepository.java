package com.github.florianehmke.qompelo.core.project;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ProjectRepository implements PanacheRepository<ProjectEntity> {}
