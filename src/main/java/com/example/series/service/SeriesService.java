package com.example.series.service;

import com.example.series.DTO.SeriesDtoRequest;
import com.example.series.DTO.SeriesDtoResponse;
import com.example.series.DTO.mapper.SeriesMapper;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.model.Actor;
import com.example.series.model.Series;
import com.example.series.repository.ActorRepository;
import com.example.series.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    private final ActorRepository actorRepository;

    public SeriesDtoResponse createSeries(SeriesDtoRequest series) {
        var seriesSave = seriesRepository.save(seriesMapper.toEntity(series));
        return seriesMapper.toDto(seriesSave);
    }

    public SeriesDtoResponse findById(Long id) {
        var series =seriesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Не существует сериала с таким id: %d".formatted(id))
        );
        return seriesMapper.toDto(series);
    }

    public List<SeriesDtoResponse> findAll() {
        var series = seriesRepository.findAll();
        return series.stream().map(seriesMapper::toDto).toList();
    }

    public SeriesDtoResponse updateSeries(Long id, SeriesDtoRequest seriesDetails) {
       Series series = seriesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Не существует сериала с таким id: %d".formatted(id)));

        seriesMapper.partialUpdate(seriesDetails, series);

        return seriesMapper.toDto(seriesRepository.save(series));
    }

    public void deleteById(Long id) {
        if (!seriesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Не существует сериала с таким id: %d".formatted(id));
        }
        seriesRepository.deleteById(id);
    }


    public void addSeriesToActor(Long studentId, Long courseId) {
        var series =seriesRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Не существует сериала с таким id: %d".formatted(studentId)));
        var actor= actorRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Не существует актера с таким id: %d".formatted(courseId)));
        series.getActors().add(actor);
        actor.getSeries().add(series);
        seriesRepository.save(series);
    }

    public boolean isActorInSeries(Long actorId, Long seriesId) {
        Optional<Series> seriesOptional = seriesRepository.findById(seriesId);
        if (seriesOptional.isPresent()) {
            Series series = seriesOptional.get();
            return series.getActors().stream()
                    .anyMatch(actor -> actor.getId().equals(actorId));
        }
        return false;
    }


}

