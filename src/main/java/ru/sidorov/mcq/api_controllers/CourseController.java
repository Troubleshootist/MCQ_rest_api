package ru.sidorov.mcq.api_controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import ru.sidorov.mcq.DTO.CourseDto;
import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.repository.CourseRepo;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.services.CourseService;
import ru.sidorov.mcq.services.ExamService;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:8081")
public class CourseController {
    private CourseRepo courseRepo;
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @GetMapping
    public Map<String, Object> getAllCourses() {
        Map<String, Object> model = new HashMap<>();
        model.put("courses", courseService.getAll());
        return model;
    }

    @GetMapping("{id}")
    public Map<String, Object> getCourseInfo(@PathVariable long id) {
        Map<String, Object> model = new HashMap<>();
        CourseDto course = courseService.getById(id);
        model.put("course", course);

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
