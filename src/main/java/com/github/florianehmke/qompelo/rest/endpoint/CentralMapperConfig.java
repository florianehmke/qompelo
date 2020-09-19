package com.github.florianehmke.qompelo.rest.endpoint;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    injectionStrategy = InjectionStrategy.FIELD,
    componentModel = "jsr330",
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CentralMapperConfig {}
