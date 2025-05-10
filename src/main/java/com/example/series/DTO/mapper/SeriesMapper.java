package com.example.series.DTO.mapper;

import com.example.series.DTO.SeriesDtoRequest;
import com.example.series.DTO.SeriesDtoResponse;
import com.example.series.model.Actor;
import com.example.series.model.Scenario;
import com.example.series.model.Series;
import org.mapstruct.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SeriesMapper {
    Series toEntity(SeriesDtoRequest seriesDtoRequest);

    @AfterMapping
    default void linkScenario(@MappingTarget Series series) {
        Scenario scenario = series.getScenario();
        if (scenario != null) {
           scenario.setSeries(series);
        }
    }

   @Mapping(target = "actorsIds", expression = "java(actorToActorIds(series.getActors()))")
    SeriesDtoResponse toDto(Series series);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Series partialUpdate(SeriesDtoRequest seriesDtoRequest, @MappingTarget Series series);

    default Set<Long> actorToActorIds(Set<Actor> actors) {
        return actors.stream()
                .map(Actor::getId)
                .collect(Collectors.toSet());
    }
}
