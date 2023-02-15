package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.CourseDto;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.repository.CourseRepo;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.utils.mapping.CourseMapper;
import ru.sidorov.mcq.utils.mapping.TrainingMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private CourseRepo courseRepo;
    private TrainingMapper trainingMapper;
    private ExamService examService;
    private StudentService studentService;
    private CourseMapper courseMapper;
    private StudentAnswerRepo studentAnswerRepo;

    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @Autowired
    public void setTrainingMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<CourseDto> getAll() {
        return courseRepo.findAll()
                .stream()
                .map(course -> {
                    CourseDto courseDto = courseMapper.mapToCourseDto(course);
                    courseDto.setExams(examService.convertExamEntityListToDtoList(course));
                    courseDto.setTraining(trainingMapper.mapToTrainingDto(course.getTraining()));
                    courseDto.setStudents(studentService.convertStudentEntityListToDtoList(course));
                    courseDto.setCorrectAnswersPercentage(getCorrectQuestionsPercentage(course));
                    return courseDto;
                })
                .collect(Collectors.toList());
    }

    public CourseDto getById(long id) {
        Course courseDao = courseRepo.findById(id).orElseThrow();
        CourseDto courseDto = courseMapper.mapToCourseDto(courseDao);
        courseDto.setCorrectAnswersPercentage(getCorrectQuestionsPercentage(courseDao));
        courseDto.setStudents(studentService.convertStudentEntityListToDtoList(courseDao));
        courseDto.setExams(examService.convertExamEntityListToDtoList(courseDao));
        courseDto.setTraining(trainingMapper.mapToTrainingDto(courseDao.getTraining()));
        return courseDto;
    }

    private double getCorrectQuestionsPercentage(Course course) {
        int courseCorrectAnswers = 0;
        int courseTotalAnswers = 0;
        for (Exam exam :
                course.getExams()) {
            courseCorrectAnswers += studentAnswerRepo.countStudentAnswerByExamAndCorrect(exam, true);
            courseTotalAnswers += studentAnswerRepo.countStudentAnswerByExam(exam);
        }
        return (double)courseCorrectAnswers/(double)courseTotalAnswers * 100;
    }

}
