package ru.sidorov.mcq.api_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.mcq.DTO.QuestionDto;
import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.services.QuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.checked;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.enabled;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:8081")
public class QuestionsController {

    private QuestionRepo questionRepo;
    private QuestionService questionService;


    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public Map<String, Object> allQuestions() {

        Map<String, Object> model = new HashMap<>();
        Iterable<Question> allQuestions = questionRepo.findAll();
        model.put("questions", allQuestions);
        return model;

    }
    @GetMapping("/filter")
    public Map<String, Object> filteredQuestions(@RequestParam long trainingID, @RequestParam List<String> checkedAtaDigitList) {
        Map<String, Object> model = new HashMap<>();
        Iterable<QuestionDto> filteredQuestionDtoList = questionService.findAllByAtaChapterInAndTrainingID(checkedAtaDigitList, trainingID);
        model.put("questions", filteredQuestionDtoList);
        return model;
    }

    @GetMapping("/actual")
    public Map<String, Object> actualQuestions() {

        Map<String, Object> model = new HashMap<>();
//        Iterable<Question> allQuestions = questionRepo.findByEnabledAndChecked(true, true);
        Iterable<Question> actualQuestions = questionRepo.findAll(where(enabled()).and(checked()));
        model.put("questions", actualQuestions);
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
    public Map<String, Object> questionByID(@PathVariable("id") long id) {
        Map<String, Object> model = new HashMap<>();
//        Question question = questionRepo.findById(id)
//                    .orElseThrow(() -> new MyEntityNotFoundException(String.format("No question with id %d", id)));
        QuestionDto questionDto = questionService.findById(id);
        model.put("Question", questionDto);

        return model;
    }

    @PostMapping
    public Question postQuestion(@RequestBody Question question) {
        return questionRepo.save(question);
    }


}
