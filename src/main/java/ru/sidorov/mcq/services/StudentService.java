package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.repository.StudentAnswerRepo;

@Service
public class StudentService {

    private StudentAnswerRepo studentAnswerRepo;

    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    public double getStudentTotalCorrectPercentage(Student student) {
        int correctAnswersCount = studentAnswerRepo.countStudentAnswerByStudentAndCorrectIsTrue(student);
        int totalAnswersCount = studentAnswerRepo.countStudentAnswerByStudent(student);
        return (double) correctAnswersCount/(double) totalAnswersCount * 100;
    }
}
