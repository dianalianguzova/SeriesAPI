package com.example.series.DTO;
import com.example.series.model.Scenario;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
/**
 * DTO for {@link Scenario}
 */
@Value
public class ScenarioDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String language;
}
