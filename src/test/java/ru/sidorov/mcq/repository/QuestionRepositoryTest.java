package ru.sidorov.mcq.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Training;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;
import static ru.sidorov.mcq.repository.specifications.QuestionSpecifications.*;

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

    @Test
    public void findByExamID() throws Exception {
        Assert.assertTrue(questionRepo.findAll(inExam(90)).size() == 28);
    }

    @Test
    public void findByInExam() throws Exception {
        Exam exam = examRepo.findById(90L).orElseThrow();
        List<Long> examQuestionIDList = exam.getQuestions().stream().map(Question::getId).toList();
        List<Long> examQuestionsIDFromCB = questionRepo.findAll(inExam(90L)).stream().map(Question::getId).toList();

        for (Long id : examQuestionsIDFromCB) {
            Assert.assertTrue(examQuestionIDList.contains(id));
        }
    }

    @Test
    public void findByNotInExam() throws Exception {
        Exam exam = examRepo.findById(90L).orElseThrow();
        List<Question> questions = questionRepo
                .findAll(where(not(inExam(exam.getId()))
                .and(inAtaChapters(exam.getAtaChapters()))
                .and(byTraining(exam.getCourse().getTraining())
                .and(enabled())
                .and(checked()))));
        System.out.println(questions);
        System.out.println(questions.size());
    }
}
