package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.CourseDto;
import ru.sidorov.mcq.DTO.QuestionDto;
import ru.sidorov.mcq.DTO.RequirementDto;
import ru.sidorov.mcq.DTO.TrainingDto;
import ru.sidorov.mcq.model.Training;
import ru.sidorov.mcq.repository.TrainingRepo;
import ru.sidorov.mcq.utils.mapping.QuestionMapper;
import ru.sidorov.mcq.utils.mapping.TrainingMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {
    private TrainingRepo trainingRepo;
    private QuestionMapper questionMapper;
    private TrainingMapper trainingMapper;

    @Autowired
    public void setTrainingMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }

    @Autowired
    public void setTrainingRepo(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public List<QuestionDto> getQuestions(Training training){
        return training.getQuestions()
                .stream()
                .map(question -> questionMapper.mapToQuestionDtoDto(question))
                .collect(Collectors.toList());
    }

    public List<RequirementDto> getRequirements(Training training) {
        return null;
    }

    public List<CourseDto> getCourses(Training entity) {
        return null;
    }

    public TrainingDto findById(long trainingID) {
        Training trainingDAO = trainingRepo.findById(trainingID).orElseThrow();
        return trainingMapper.mapToTrainingDto(trainingDAO);
    }
}
