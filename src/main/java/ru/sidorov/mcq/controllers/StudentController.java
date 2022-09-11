package ru.sidorov.mcq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.repository.StudentRepo;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private StudentRepo studentRepo;

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("No student found" + id)); // Больше информации string template
    }

    @PutMapping("{id}")
    public Student changeStudent(@RequestBody Student newStudent, @PathVariable long id) {
        return studentRepo.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setSurname(newStudent.getSurname());
                    student.setDob(newStudent.getDob());
                    student.setCourse(newStudent.getCourse());
                    return studentRepo.save(student);
                })
                .orElseGet(() -> {
                    return studentRepo.save(newStudent);
                });
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
        } else {
            throw new MyEntityNotFoundException("No student found");
        }
    }
}
