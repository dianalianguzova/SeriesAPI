package com.example.series.controller;

import com.example.series.DTO.ActorDtoResponse;
import com.example.series.DTO.ScenarioDto;
import com.example.series.DTO.SeriesDtoRequest;
import com.example.series.DTO.SeriesDtoResponse;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.model.Actor;
import com.example.series.service.ActorService;
import com.example.series.service.ScenarioService;
import com.example.series.service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;
    private final ScenarioService scenarioService;
    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<SeriesDtoResponse>> getAllSeries() {
        return ResponseEntity.status(HttpStatus.OK).body(seriesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesDtoResponse> getSeriesById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(seriesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SeriesDtoResponse> createSeries(@Valid @RequestBody SeriesDtoRequest student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seriesService.createSeries(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesDtoResponse> updateSeries(@PathVariable Long id, @Valid @RequestBody SeriesDtoRequest student) {
        return ResponseEntity.status(HttpStatus.OK).body(seriesService.updateSeries(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        seriesService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/scenario")
    public ResponseEntity<ScenarioDto> getScenario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(scenarioService.getScenario(id));
    }

    @PutMapping("/{id}/scenario")
    public ResponseEntity<ScenarioDto> updateScenario(@PathVariable Long id, @Valid @RequestBody ScenarioDto contactInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(scenarioService.updateScenario(id, contactInfo));
    }

    @GetMapping("/{seriesId}/actors/{actorsId}")
    public ResponseEntity<ActorDtoResponse> getActorFromSeries(@PathVariable Long seriesId, @PathVariable Long actorsId) {
        ActorDtoResponse actor = actorService.getActorById(actorsId);

        if (actor == null || !actorService.isActorInSeries(actorsId, seriesId)) {
            throw new ResourceNotFoundException("Actor not not found with id: " + seriesId);
          //  return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(actorService.getActorById(actorsId));
    }

    @PostMapping("/{seriesId}/actors/{actorId}")
    public ResponseEntity<Void> addActorToSeries(@PathVariable Long seriesId, @PathVariable Long actorId) {
        seriesService.addSeriesToActor(seriesId,actorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
