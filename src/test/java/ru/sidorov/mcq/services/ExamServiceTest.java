package ru.sidorov.mcq.services;


import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.QualifiedIdentifier;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;


import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.AtaChapterRepository;
import ru.sidorov.mcq.repository.CourseRepo;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.QuestionRepo;

import static org.springframework.data.jpa.domain.Specification.where;
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


    @Test
    public void examQuestionsGreaterThen11Test() throws Exception {

        for (Exam exam : examRepo.findAll()) {
            Assert.assertTrue(exam.getQuestions().size() > 11);
        }
    }


    @Test
    @Repeat(value = 20)
    public void examCreationAndDeleteTest() throws Exception {
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
        exam.getQuestions().stream().map(Question::getId).forEach(System.out::println);
        examRepo.save(exam);

        Assert.assertTrue(exam.getQuestions().size() > 11);
        Assert.assertEquals(0, exam.getQuestions().size() % 4);

        examRepo.delete(exam);

        List<Question> deletedQuestions = questionRepo.findAll(inExam(exam));
        Assert.assertEquals(0, deletedQuestions.size());

        Assert.assertEquals(exam.getQuestions().size(), exam.getQuestions().stream().map(Question::getId).distinct().toList().size());

    }


}
