package ru.sidorov.mcq.services.examhelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Requirement;
import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.repository.RequirementRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AddMinimumQuestionsHelper {

    private RequirementRepo requirementRepo;
    private QuestionRepo questionRepo;

    @Autowired
    public void setRequirementRepo(RequirementRepo requirementRepo) {
        this.requirementRepo = requirementRepo;
    }

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public void addMinimumQuestions(Exam exam) { // выносить в Helpers
    	List<Question> examQuestions = new ArrayList<>();
        for (AtaChapter ataChapter: exam.getAtaChapters()) {
            Requirement requirement = requirementRepo.findByTrainingAndAtaChapter(exam.getCourse().getTraining(), ataChapter);
            if (requirement != null) {
                List<Question> questionsPart = questionRepo.findByAtaChapterAndLevelAndEnabledIsTrueAndCheckedIsTrueAndTraining(ataChapter, requirement.getLevel(), exam.getCourse().getTraining());
                
                // Из-за того что не получается сделать запрос Order by random и Limit в HQL запросе - придется это делать в коде
                Collections.shuffle(questionsPart);
                List<Question> preparedQuestions = questionsPart.stream()
                .limit(requirement.getQuestionsNumber())
                .toList();
                examQuestions.addAll(preparedQuestions);
            }

        }
        exam.setQuestions(examQuestions);

    }
}
