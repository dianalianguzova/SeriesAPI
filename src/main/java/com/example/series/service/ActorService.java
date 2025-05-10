package com.example.series.service;

import com.example.series.DTO.ActorDtoRequest;
import com.example.series.DTO.ActorDtoResponse;
import com.example.series.DTO.mapper.ActorMapper;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.model.Actor;
import com.example.series.model.Award;
import com.example.series.repository.ActorRepository;
import com.example.series.repository.AwardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Transactional
@RequiredArgsConstructor
@Service
public class ActorService {
    private final ActorMapper actorMapper;
    private final ActorRepository actorRepository;
    private final AwardRepository awardRepository;


    public List<ActorDtoResponse> getAllActors() {
        return actorRepository.findAll().stream().map(actorMapper::toDto).toList();
    }

    public ActorDtoResponse getActorById(Long id) {
        var course = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not not found with id: " + id));
        return actorMapper.toDto(course);
    }

    public ActorDtoResponse createActor(ActorDtoRequest course) {
        var courseEntity = actorRepository.save(actorMapper.toEntity(course));
        return actorMapper.toDto(courseEntity);
    }

    public ActorDtoResponse updateActor(Long id, ActorDtoRequest courseDetails) {
        var course = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));

        actorMapper.partialUpdate(courseDetails, course);

        return actorMapper.toDto(actorRepository.save(course));
    }

    public void deleteActor(Long id) {
        if (!actorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Actor not not found with id: " + id);
        }
        actorRepository.deleteById(id);
    }


    public boolean isActorInSeries(Long actorsId, Long seriesId) {
        Optional<Actor> actorOptional = actorRepository.findById(actorsId);

        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            return actor.getSeries().stream()
                    .anyMatch(series -> series.getId().equals(seriesId));
        }

        return false;
    }

    public boolean isActorInAwards(Long actorId, Long awardId) {
        Optional<Award> awardOptional = awardRepository.findById(awardId);
        if (awardOptional.isPresent()) {
            Award award = awardOptional.get();
            return award.getActor().getId().equals(actorId);
        }
        return false;
    }
}
