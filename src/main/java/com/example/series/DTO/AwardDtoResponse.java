package com.example.series.DTO;

import com.example.series.model.Actor;
import com.example.series.model.Award;
import lombok.Value;
/**
 * DTO for {@link com.example.series.model.Award}
 */
@Value
public class AwardDtoResponse {
    String name;
    int yearReceipt;
    Long actorId;
}
