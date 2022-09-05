package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.mcq.repository.TrainingRepo;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/trainings")
public class TrainingController {

    private TrainingRepo trainingRepo;

    @Autowired
    public void setTrainingRepo(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    @GetMapping
    public Map<String, Object> getAllTrainings() {
        Map<String, Object> model = new HashMap<>();
        model.put("trainings", trainingRepo.findAll());
        return model;
    }


}
