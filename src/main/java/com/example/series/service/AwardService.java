package com.example.series.service;//package com.example.demo.service;

import com.example.series.DTO.ActorDtoRequest;
import com.example.series.DTO.ActorDtoResponse;
import com.example.series.DTO.AwardDtoRequest;
import com.example.series.DTO.AwardDtoResponse;
import com.example.series.DTO.mapper.AwardMapper;
import com.example.series.exception.ResourceNotFoundException;
import com.example.series.model.Actor;
import com.example.series.model.Award;
import com.example.series.repository.ActorRepository;
import com.example.series.repository.AwardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AwardService {
    private final AwardRepository awardRepository;
    private final ActorRepository actorRepository;


    public List<AwardDtoResponse> getAllAwards() {
        List<Award> aw = awardRepository.findAll();
        return awardRepository.findAll().stream().map(this::convertToDto).toList();
   }

    public AwardDtoResponse convertToDto(Award award) {
        if (award == null) {
            return null;
        }
        String name = award.getName();
        int yearReceipt = award.getYearReceipt();

        Actor actor = award.getActor();
        Long actorId = (actor != null) ? actor.getId() : null; // Извлечение ID актера

        return new AwardDtoResponse(name, yearReceipt, actorId);
    }

    public AwardDtoResponse updateAward(Long id, AwardDtoRequest awardDetails) {
        var award = awardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не существует награды с таким id: " + id));

        if (awardDetails.getName() != null) {
            award.setName(awardDetails.getName());
        }
        if (awardDetails.getYearReceipt() != null) {
            award.setYearReceipt(Integer.parseInt(awardDetails.getYearReceipt()));
        }
        if (awardDetails.getActorId() != null) {
            var actor = actorRepository.findById(awardDetails.getActorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Актёр не найден с id: " + awardDetails.getActorId()));
            award.setActor(actor);
        }

        return convertToDto(awardRepository.save(award));
    }

    public AwardDtoResponse getAwardById(Long id) {
        var award = awardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Награда не найдена с id: " + id));

        return convertToDto(award);  // Ваш метод преобразования
    }

    public AwardDtoResponse createAward(AwardDtoRequest award) {
        if (award == null) {
            return null;
        }

        Award award1 = new Award();
        award1.setName(award.getName());

        if (award.getYearReceipt() != null) {
            award1.setYearReceipt(Integer.parseInt(award.getYearReceipt()));
        }

        Long actorId = award.getActorId();
        actorRepository.findById(actorId)
                .ifPresentOrElse(award1::setActor,
                        () -> { throw new ResourceNotFoundException("Actor not found with id: " + actorId); });

        return convertToDto(awardRepository.save(award1));
    }

    public void deleteAward(Long id) {
        if (!awardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Награда не найдена с id: " + id);
        }
        awardRepository.deleteById(id);
    }
}
