package com.example.series.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

/**
 * DTO for {@link com.example.series.model.Actor}
 */
@Value
public class ActorDtoResponse {
    String name;
    String bio;
    Set<Long> awardIds;
    Set<Long> seriesIds;
}