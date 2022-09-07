package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.sidorov.mcq.exceptions.EntityNotFoundException;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.services.ExamService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("{id}")
    public Map<String, Object> showExamInfo(@PathVariable("id") long id) {
        Map<String, Object> model = new HashMap<>();
        Exam exam = examRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No exam found"));
        model.put("exam", exam);
        model.put("questions_count", exam.getQuestions().size());
        return model;
    }

    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examRepo.save(examService.createExam(exam));
    }

    @DeleteMapping("{id}")
    public void deleteExam(@PathVariable(value = "id") Long id) {
        if (examRepo.existsById(id)) {
            examRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("No exam with this id");
        }
        
    }

}
