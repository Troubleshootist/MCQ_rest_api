package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.mcq.model.Question;

import ru.sidorov.mcq.repository.QuestionRepo;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QuestionsController {

    private QuestionRepo questionRepo;

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @GetMapping("/api/questions")
    public Map<String, Object> allQuestions() {

        Map<String, Object> model = new HashMap<>();
        Iterable<Question> allQuestions = questionRepo.findAll();

        model.put("questions", allQuestions);
        return model;

    }

    @GetMapping("/api/questions/actual")
    public Map<String, Object> actualQuestions() {

        Map<String, Object> model = new HashMap<>();
        Iterable<Question> allQuestions = questionRepo.findQuestionsByEnabledAndChecked(true, true);

        model.put("questions", allQuestions);
        return model;

    }

    @GetMapping("/api/questions/disabled")
    public Map<String, Object> disabledQuestions() {
        Map<String, Object> model = new HashMap<>();
        Iterable<Question> disabledQuestions = questionRepo.findQuestionsByEnabled(false);

        model.put("questions", disabledQuestions);
        return model;
    }

}
