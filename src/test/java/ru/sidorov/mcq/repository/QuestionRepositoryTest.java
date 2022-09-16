package ru.sidorov.mcq.repository;

import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.byTraining;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.checked;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.enabled;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.inAtaChapters;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.inExam;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Training;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private TrainingRepo trainingRepo;

    @Autowired
    private ExamRepo examRepo;

    @Test
    public void findByTraining() throws Exception {
        Training training = trainingRepo.findById(5L).orElseThrow();
        Assert.assertNotNull(questionRepo.findAll(byTraining(training)));
    }

    @Test
    public void findAllCheckedAndEnabled() throws Exception {
        Assert.assertNotNull(questionRepo.findAll(where(checked()).and(enabled())));
    }

    @Test
    public void findAllChecked() throws Exception {
        Assert.assertNotNull(questionRepo.findAll(checked()));
    }

    @Test
    public void findAllEnabled() throws Exception {
        Assert.assertNotNull(questionRepo.findAll(enabled()));
    }

    @Test
    public void findByAtaChaptersList() throws Exception {
        Exam exam = examRepo.findById(89L).orElseThrow();
        Assert.assertNotNull(questionRepo.findAll(inAtaChapters(exam.getAtaChapters())));
    }

    // @Test
    // public void findByExamID() throws Exception {
    //     Assert.assertEquals(28, questionRepo.findAll(inExam(90)).size());
    // }

    @Test
    public void findByInExam() throws Exception {
        Exam exam = examRepo.findById(90L).orElseThrow();
        List<Long> examQuestionIDList = exam.getQuestions().stream().map(Question::getId).toList();
        List<Long> examQuestionsIDFromCB = questionRepo
                .findAll(inExam(exam))
                .stream()
                .map(Question::getId)
                .toList();

        for (Long id : examQuestionsIDFromCB) {
            Assert.assertTrue(examQuestionIDList.contains(id));
        }
    }

    @Test
    @Repeat(value = 100)
    public void findByNotInExam() throws Exception {
        List<Exam> allExams = examRepo.findAll();
        Collections.shuffle(allExams);
        Exam examForTest = allExams.get(0);
        Exam exam = examRepo.findById(examForTest.getId()).orElseThrow();
        List<Question> questionsNotInExam = questionRepo
                .findAll(not(inExam(exam))
                .and(inAtaChapters(exam.getAtaChapters()))
                .and(byTraining(exam.getCourse().getTraining()))
                .and(enabled())
                .and(checked()));
        List<Long> questionsNotInExamIDList = questionsNotInExam.stream().map(Question::getId).toList();
        for (Question question : exam.getQuestions()) {
            Assert.assertFalse(questionsNotInExamIDList.contains(question.getId()));
        }
        List<Long> currentExamQuestionsIDList = exam.getQuestions()
                .stream()
                .map(Question::getId)
                .toList();
        List<Long> intersections = questionsNotInExamIDList
                .stream()
                .distinct()
                .filter(currentExamQuestionsIDList :: contains)
                .toList();
        Assert.assertEquals(0, intersections.size());

    }

}
