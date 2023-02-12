package ru.sidorov.mcq.utils.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.TrainingDto;
import ru.sidorov.mcq.model.Training;
import ru.sidorov.mcq.services.TrainingService;

@Service
public class TrainingMapper {
    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    public TrainingDto mapToTrainingDto(Training entity) {
        TrainingDto dto = new TrainingDto(
                entity.getName(),
                trainingService.getQuestions(entity),
                trainingService.getRequirements(entity),
                trainingService.getCourses(entity)
        );
        return dto;
    }
}
