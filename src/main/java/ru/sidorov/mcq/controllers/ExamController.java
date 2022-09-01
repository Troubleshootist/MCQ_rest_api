package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.mcq.repository.ExamRepo;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExamController {
    private ExamRepo examRepo;

    @Autowired
    public void setExamRepo(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    @GetMapping("api/exams")
    public Map<String, Object> allExams() {
        Map<String, Object> model = new HashMap<>();
        model.put("exams", examRepo.findAll());
        return model;
    }

}
