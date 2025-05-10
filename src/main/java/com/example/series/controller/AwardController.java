package com.example.series.controller;

import com.example.series.DTO.ActorDtoRequest;
import com.example.series.DTO.ActorDtoResponse;
import com.example.series.DTO.AwardDtoRequest;
import com.example.series.DTO.AwardDtoResponse;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.service.ActorService;
import com.example.series.service.AwardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awards")
@RequiredArgsConstructor
public class AwardController {
    @Autowired
    private final AwardService awardService;
    private final ActorService actorService;

    @GetMapping
    public List<AwardDtoResponse> getAllAwards() {
        return awardService.getAllAwards();
    }

    @GetMapping("/{id}")
    public AwardDtoResponse getAwardById(@PathVariable Long id) {
        return awardService.getAwardById(id);
    }

    @GetMapping("/{id}/actor")
    public ResponseEntity<ActorDtoResponse> getActorsByAward(@PathVariable Long id) {
        AwardDtoResponse award = awardService.getAwardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(actorService.getActorById(award.getActorId()));
    }


    @PostMapping
    public AwardDtoResponse createAward(@Valid @RequestBody AwardDtoRequest award) {
        return awardService.createAward(award);
    }

    @DeleteMapping("/{id}")
    public void deleteAward(@PathVariable Long id) {
        awardService.deleteAward(id);
    }

    @PutMapping("/{id}")
    public AwardDtoResponse updateAward(@PathVariable Long id, @Valid @RequestBody AwardDtoRequest awardDetails) {
        return awardService.updateAward(id, awardDetails);
    }
}
