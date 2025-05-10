package com.example.series.controller;

import com.example.series.DTO.ActorDtoRequest;
import com.example.series.DTO.ActorDtoResponse;
import com.example.series.DTO.SeriesDtoResponse;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.service.ActorService;
import com.example.series.service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;
    private final SeriesService seriesService;

    @GetMapping
    public List<ActorDtoResponse> getAllActor() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public ActorDtoResponse getActorById(@PathVariable Long id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public ActorDtoResponse createActor(@Valid @RequestBody ActorDtoRequest actor) {
        return actorService.createActor(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }

    @PutMapping("/{id}")
    public ActorDtoResponse updateActor(@PathVariable Long id, @Valid @RequestBody ActorDtoRequest actorDetails) {
        return actorService.updateActor(id, actorDetails);
    }

    @GetMapping("/{actorsId}/series/{seriesId}")
    public ResponseEntity<SeriesDtoResponse> getSeriesFromActor(@PathVariable Long actorsId, @PathVariable Long seriesId) {
        SeriesDtoResponse series =seriesService.findById(seriesId);

        if (series == null || !seriesService.isActorInSeries(actorsId, seriesId)) {
            throw new ResourceNotFoundException("Series not not found with id: " + seriesId);
            //  return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(seriesService.findById(seriesId));
    }




}
