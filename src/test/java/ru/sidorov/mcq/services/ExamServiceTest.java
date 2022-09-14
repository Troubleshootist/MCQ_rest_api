package ru.sidorov.mcq.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.repository.AtaChapterRepository;
import ru.sidorov.mcq.repository.CourseRepo;
import ru.sidorov.mcq.repository.ExamRepo;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTest {

    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private AtaChapterRepository ataChapterRepository;

    @Autowired
    private ExamService examService;


    @Test
    public void examQuestionsGreaterThen11Test() throws Exception {

        for (Exam exam : examRepo.findAll()) {
            Assert.assertTrue(exam.getQuestions().size() > 11);
        }
    }

    @Test
    public void examQuestionsCanBeDividedByFour() throws Exception {
        for (Exam exam : examRepo.findAll()) {
            Assert.assertTrue(exam.getQuestions().size() % 4 == 0);
        }
    }

    @Test
    public void examCreationTest() throws Exception {
        Course course = courseRepo.findById(19L).orElseThrow();
        List<AtaChapter> ataChaptersForTest = ataChapterRepository.findByAtaDigitIn(Arrays.asList("32", "32A", "36", "21"));

        Exam exam = new Exam();
        exam.setCourse(course);
        exam.setAtaChapters(ataChaptersForTest);
        exam.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        exam.setNoteForExaminer("This note from autotest");
        examService.createExam(exam);
        

        Assert.assertTrue(exam.getQuestions().size() > 11);
        Assert.assertTrue(exam.getQuestions().size() % 4 == 0);

//        examRepo.delete(exam);

    }
}
