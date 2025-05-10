package com.example.series.DTO;

import com.example.series.model.Award;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link Award}
 */
@Value
public class AwardDtoRequest {
    @NotBlank
    private String name;
    @NotNull
    @Min(1900)
    @Max(2025)
    private String yearReceipt;
    @NotNull
    private Long actorId;
}
