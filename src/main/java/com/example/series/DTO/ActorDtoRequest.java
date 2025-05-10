package com.example.series.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for {@link com.example.series.model.Actor}
 */
@Value
public class ActorDtoRequest {
    @NotBlank
    String name;
    @NotBlank
    String bio;
}