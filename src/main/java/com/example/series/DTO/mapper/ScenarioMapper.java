package com.example.series.DTO.mapper;

import com.example.series.DTO.ScenarioDto;
import com.example.series.model.Scenario;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScenarioMapper {
    Scenario toEntity(ScenarioDto scenarioDto);

    ScenarioDto toDto(Scenario scenario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Scenario partialUpdate(ScenarioDto scenarioDto, @MappingTarget Scenario scenario);
}