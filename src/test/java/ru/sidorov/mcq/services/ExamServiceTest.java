package ru.sidorov.mcq.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.inExam;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTest {

    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private AtaChapterRepository ataChapterRepository;

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentAnswerRepo studentAnswerRepo;


    @Test
    public void examQuestionsGreaterThen11Test() throws Exception {

        for (Exam exam : examRepo.findAll()) {
            Assert.assertTrue(exam.getQuestions().size() > 11);
        }
    }


    @Test
    @Repeat(value = 20)
    public void examShouldBeCreatedCorrectAndDeleted() throws Exception {
        Course course = courseRepo.findById(16L).orElseThrow();
        List<AtaChapter> allAtaChapters = ataChapterRepository.findAll();
        Collections.shuffle(allAtaChapters);
        List<AtaChapter> ataChaptersForTest = allAtaChapters.stream().limit(13).toList();

        Exam exam = new Exam();
        exam.setCourse(course);
        exam.setAtaChapters(ataChaptersForTest);
        exam.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        exam.setNoteForExaminer("This note from autotest");

        examService.createExam(exam);
//        exam.getQuestions().stream().map(Question::getId).forEach(System.out::println);
        examRepo.save(exam);

        Assert.assertTrue(exam.getQuestions().size() > 11);
        Assert.assertEquals(0, exam.getQuestions().size() % 4);

        // check for unique questions in exam
        Assert.assertEquals(exam.getQuestions().size(), exam.getQuestions().stream().map(Question::getId).distinct().toList().size());

        examRepo.delete(exam);

        List<Question> deletedQuestions = questionRepo.findAll(inExam(exam));
        Assert.assertEquals(0, deletedQuestions.size());


    }

    @Test
    public void examCheckedTest() {
        Exam exam = examRepo.findById(79L).orElseThrow();
        long examAnswersCountFromRepo = studentAnswerRepo.findByExam(exam).size();
        int estimatedAnswersCount = exam.getQuestions().size() * exam.getCourse().getStudents().size();
        Assert.assertEquals(estimatedAnswersCount, examAnswersCountFromRepo);
    }


}
