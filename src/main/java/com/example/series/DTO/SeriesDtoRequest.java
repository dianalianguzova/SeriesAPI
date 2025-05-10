package com.example.series.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
/**
 * DTO for {@link com.example.series.model.Series}
 */
@Value
public class SeriesDtoRequest {
    @NotBlank
    private String name;
    @NotNull(message = "Рейтинг не может быть нулевым")
    @Min(value = 1, message = "Рейтинг не может быть меньше 1")
    @Max(value = 10, message = "Рейтинг не может превысить 10")
    private int rating;
    @Valid
    ScenarioDto scenario;
}
