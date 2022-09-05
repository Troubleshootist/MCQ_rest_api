package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.services.ExamService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/exams")
public class ExamController {
    private ExamRepo examRepo;

    @Autowired
    private ExamService examService;
    @Autowired
    public void setExamRepo(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    @GetMapping
    public Map<String, Object> allExams() {
        Map<String, Object> model = new HashMap<>();
        model.put("exams", examRepo.findAll());
        return model;
    }

    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examRepo.save(examService.createExam(exam));
    }

}
