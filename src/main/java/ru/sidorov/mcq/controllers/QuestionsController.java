package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.sidorov.mcq.model.Answer;
import ru.sidorov.mcq.model.Question;

import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.repository.UserRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/api/questions/{id}")
    public ResponseEntity<Question> questionByID(@PathVariable("id") long id) {
        Optional<Question> questionByID = questionRepo.findById(id);
        if (questionByID.isPresent()) {
            return new ResponseEntity<>(questionByID.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/questions/")
    public Question postQuestion(@RequestBody Question question) {
        return questionRepo.save(question);
    }


}
