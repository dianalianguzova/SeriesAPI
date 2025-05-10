package com.example.series.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "scenario")
public class Scenario {
    @Id
    private Long id;

    private String title;
    private String author;
    private String language;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Series series;
}
