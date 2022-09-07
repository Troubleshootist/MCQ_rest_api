package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.sidorov.mcq.exceptions.EntityNotFoundException;
import ru.sidorov.mcq.model.Question;

import ru.sidorov.mcq.repository.QuestionRepo;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    private QuestionRepo questionRepo;

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @GetMapping
    public Map<String, Object> allQuestions() {

        Map<String, Object> model = new HashMap<>();
        Iterable<Question> allQuestions = questionRepo.findAll();

        model.put("questions", allQuestions);
        return model;

    }

    @GetMapping("/actual")
    public Map<String, Object> actualQuestions() {

        Map<String, Object> model = new HashMap<>();
        Iterable<Question> allQuestions = questionRepo.findByEnabledAndChecked(true, true);

        model.put("questions", allQuestions);
        return model;

    }

    @GetMapping("/disabled")
    public Map<String, Object> disabledQuestions() {
        Map<String, Object> model = new HashMap<>();
        Iterable<Question> disabledQuestions = questionRepo.findByEnabled(false);

        model.put("questions", disabledQuestions);
        return model;
    }

    @GetMapping("{id}")
    public Question questionByID(@PathVariable("id") long id) {
        return questionRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Question not found"));
   
    }

    @PostMapping
    public Question postQuestion(@RequestBody Question question) {
        return questionRepo.save(question);
    }


}
