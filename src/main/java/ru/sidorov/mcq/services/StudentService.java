package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.StudentDto;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.utils.mapping.CourseMapper;
import ru.sidorov.mcq.utils.mapping.StudentAnswerMapper;
import ru.sidorov.mcq.utils.mapping.StudentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentAnswerRepo studentAnswerRepo;
    private StudentMapper studentMapper;
    private CourseMapper courseMapper;
    private StudentAnswerService studentAnswerService;

    @Autowired
    public void setStudentAnswerService(StudentAnswerService studentAnswerService) {
        this.studentAnswerService = studentAnswerService;
    }

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    public double getStudentTotalCorrectPercentage(Student student) {
        int correctAnswersCount = studentAnswerRepo.countStudentAnswerByStudentAndCorrectIsTrue(student);
        int totalAnswersCount = studentAnswerRepo.countStudentAnswerByStudent(student);
        return (double) correctAnswersCount/(double) totalAnswersCount * 100;
    }

    public List<StudentDto> convertStudentEntityListToDtoList(Course course) {
        return course.getStudents()
                .stream()
                .map(student -> {
                    StudentDto studentDto = studentMapper.mapToStudentDto(student);
                    studentDto.setCourseStats(getStudentTotalCorrectPercentage(student));
                    return studentDto;
                })
                .collect(Collectors.toList());
    }
}
