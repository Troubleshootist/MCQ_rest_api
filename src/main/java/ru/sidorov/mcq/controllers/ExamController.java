package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.services.ExamService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/exams")
public class ExamController {
    private ExamRepo examRepo;
    private ExamService examService;

    @Autowired
    public void setExamRepo(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }
    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public Map<String, Object> allExams() {
        Map<String, Object> model = new HashMap<>();
        model.put("exams", examRepo.findAll());
        return model;
    }

    @GetMapping("{id}")
    public Map<String, Object> showExamInfo(@PathVariable("id") long id) {
        Map<String, Object> model = new HashMap<>();
        Exam exam = examRepo.findById(id)
                    .orElseThrow(() -> new MyEntityNotFoundException(String.format("No exam with id %d", id)));
        model.put("exam", exam);
        model.put("questions_count", exam.getQuestions().size());
        return model;
    }

    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examRepo.save(examService.createExam(exam));
    }

    @PostMapping("{initialExamId}/reexam")
    public Exam createReexam(@RequestBody Exam exam, @PathVariable long initialExamId) {
        return examService.createReexam(exam, initialExamId);
    }

    @DeleteMapping("{id}")
    public void deleteExam(@PathVariable(value = "id") Long id) {
        if (examRepo.existsById(id)) {
            examRepo.deleteById(id);
        } else {
            throw new MyEntityNotFoundException("No exam with this id");
        }
    }

    @GetMapping("{id}/statistics")
    public Map<String, Object> statistics(@PathVariable("id") long id) {
        Exam exam = examRepo.findById(id).orElseThrow(() -> new MyEntityNotFoundException(String.format("No exam with id %d", id)));
        Map<String, Object> model = new HashMap<>();
        model.put("Total questions", exam.getQuestions().size());
        model.put("Correct answers percentage", examService.getCorrectPercentage(exam));
        model.put("Correct percentage by questions", examService.getPercentageByQuestions(exam));
        model.put("Correct percentage by students", examService.getPercentageByStudents(exam));
        return model;
    }


}
