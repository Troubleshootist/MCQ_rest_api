package ru.sidorov.mcq.services.examhelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.QuestionRepo;

import java.util.Collections;
import java.util.List;

@Service
public class AddAdditionalQuestionsToDivideByFourHelper {

    private QuestionRepo questionRepo;

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public void addAdditionalQuestionsToDivideByFour(Exam exam) {
        int reminder = 4 - exam.getQuestions().size() % 4;
        if (reminder != 4) {
            List<Long> restrictedQuestionsIdList = exam.getQuestions().stream().map(Question::getId).toList();
            List<Question> additionalQuestions = questionRepo.findByTrainingAndEnabledIsTrueAndCheckedIsTrueAndAtaChapterInAndIdNotIn(exam.getCourse().getTraining(), exam.getAtaChapters(), restrictedQuestionsIdList);
            Collections.shuffle(additionalQuestions);
            List<Question> examQuestions = exam.getQuestions();
            examQuestions.addAll(additionalQuestions.stream().limit(reminder).toList());
            exam.setQuestions(examQuestions);
        }
    }
}
