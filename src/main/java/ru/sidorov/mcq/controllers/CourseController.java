package ru.sidorov.mcq.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Course;
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

    @GetMapping("{id}")
    public Map<String, Object> getCourseInfo(@PathVariable long id) {
        Map<String, Object> model = new HashMap<>();
        Course course = courseRepo.findById(id)
                    .orElseThrow(() -> new MyEntityNotFoundException("Course not found"));
        model.put("course", course);
        model.put("students", course.getStudents());
        model.put("exams", course.getExams());
        return model;
    }
    
    @PutMapping("{id}")
    public Course changeCourse(@RequestBody Course newCourse, @PathVariable long id) {
    	return courseRepo.findById(id) 
    			.map(course -> {
    				course.setCourseNumber(newCourse.getCourseNumber());
    				course.setExams(newCourse.getExams());
    				course.setTraining(newCourse.getTraining());
    				course.setStudents(newCourse.getStudents());
    				course.setCourseNumber(newCourse.getCourseNumber());
    				return course;
    			})
    			.orElseGet(() -> {
    				return courseRepo.save(newCourse);
    			});
    			
    }
    
    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable long id) {
    	if (courseRepo.existsById(id)) {
    		courseRepo.deleteById(id);
    	} else {
    		throw new MyEntityNotFoundException("Course not found");
    	}
    }
    
    
}
