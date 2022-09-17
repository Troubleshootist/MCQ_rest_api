package ru.sidorov.mcq.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.repository.StudentRepo;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    ExamRepo examRepo;

    @Autowired
    StudentAnswerRepo studentAnswerRepo;


    @Test
    @Transactional
    @Repeat(value = 10)
    public void studentResultsTest() throws Exception {

        Exam exam = examRepo.findById(65L).orElseThrow();
        List<Student> examStudents = exam.getCourse().getStudents();
        Collections.shuffle(examStudents);
        Student randStudent = examStudents.get(0);
        long incorrectAnswersCount = studentAnswerRepo.findByExamAndStudent(exam, randStudent)
                .stream()
                .filter(a -> !a.isCorrect())
                .count();
        double correctPercentage = 100 - (double)incorrectAnswersCount/(double)exam.getQuestions().size()*100;
        Assert.assertTrue(correctPercentage >= 0 && correctPercentage <= 100);
        System.out.println("end");
    }

    @Test
    public void examResultTest() throws Exception {
        Exam exam = examRepo.findById(65L).orElseThrow();
        long examIncorrectQuestionsCount = studentAnswerRepo.findByExam(exam)
                .stream()
                .filter(a -> !a.isCorrect())
                .count();
        double examCorrectPercentage = 100 - (double)examIncorrectQuestionsCount/(double)exam.getQuestions().size();
        Assert.assertTrue(examCorrectPercentage >= 0 && examCorrectPercentage <=100);
        System.out.println(examCorrectPercentage);
    }
}
