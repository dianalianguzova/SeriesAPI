package com.example.series.DTO.mapper;

import com.example.series.DTO.ActorDtoRequest;
import com.example.series.DTO.ActorDtoResponse;
import com.example.series.model.Actor;
import com.example.series.model.Award;
import com.example.series.model.Series;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActorMapper {
    Actor toEntity(ActorDtoRequest actorDto);

    @Mapping(target = "seriesIds", expression = "java(seriesToSeriesIds(actor.getSeries()))")
    @Mapping(target = "awardIds", expression = "java(awardToAwardIds(actor.getAwards()))")
    ActorDtoResponse toDto(Actor actor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actor partialUpdate(ActorDtoRequest actorDto, @MappingTarget Actor actor);

    default Set<Long> awardToAwardIds(Set<Award> awards) {
        return awards.stream().map(Award::getId).collect(Collectors.toSet());
    }

    default Set<Long> seriesToSeriesIds(Set<Series> series) {
        return series.stream().map(Series::getId).collect(Collectors.toSet());
    }
}