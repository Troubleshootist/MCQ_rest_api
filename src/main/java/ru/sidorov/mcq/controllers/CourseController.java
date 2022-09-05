package ru.sidorov.mcq.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sidorov.mcq.repository.CourseRepo;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private CourseRepo courseRepo;

    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @GetMapping
    public Map<String, Object> getAllCourses() {
        Map<String, Object> model = new HashMap<>();
        model.put("courses", courseRepo.findAll());
        return model;
    }
    
}
