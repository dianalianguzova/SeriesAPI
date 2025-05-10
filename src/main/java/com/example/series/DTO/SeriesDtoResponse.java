package com.example.series.DTO;

import com.example.series.model.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;
/**
 * DTO for {@link com.example.series.model.Series}
 */
@Value
public class SeriesDtoResponse {
    private String name;
    private int rating;
    Scenario scenario;
    Set<Long> actorsIds;
}
