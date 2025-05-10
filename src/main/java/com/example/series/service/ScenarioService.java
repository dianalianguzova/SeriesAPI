package com.example.series.service;

import com.example.series.DTO.ScenarioDto;
import com.example.series.DTO.mapper.ScenarioMapper;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.repository.ScenarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final ScenarioMapper scenarioMapper;

    public ScenarioDto getScenario(Long id) {
        var info = scenarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует сценария с таким id: %d".formatted(id)));
        return scenarioMapper.toDto(info);
    }

    public ScenarioDto updateScenario(Long id, ScenarioDto contactDetails) {
        var contactInfo = scenarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует сценария с таким id: %d".formatted(id)));

        scenarioMapper.partialUpdate(contactDetails, contactInfo);

        return scenarioMapper.toDto(scenarioRepository.save(contactInfo));
    }
}