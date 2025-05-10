package com.example.series.DTO.mapper;

import com.example.series.DTO.AwardDtoRequest;
import com.example.series.DTO.AwardDtoResponse;
import com.example.series.model.Award;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
       componentModel = MappingConstants.ComponentModel.SPRING)
public interface AwardMapper {
    Award toEntity(AwardDtoRequest awardDto);

 //  AwardDtoResponse toDto(Award award);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Award partialUpdate(AwardDtoRequest awardDto, @MappingTarget Award award);
}
