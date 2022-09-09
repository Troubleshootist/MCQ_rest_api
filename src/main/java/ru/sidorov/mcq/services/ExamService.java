package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Requirement;
import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.repository.RequirementRepo;

import java.util.*;

@Service
public class ExamService {


    private QuestionRepo questionRepo;
    private RequirementRepo requirementRepo;



    @Autowired
    public void setRequirementRepo(RequirementRepo requirementRepo) {
        this.requirementRepo = requirementRepo;
    }

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

  

    public Exam createExam(Exam exam) {
        List<Question> examQuestions = new ArrayList<>();
        for (AtaChapter ataChapter: exam.getAtaChapters()) {
            Requirement requirement = requirementRepo.findByTrainingAndAtaChapter(exam.getCourse().getTraining(), ataChapter);
            List<Question> questionsPart = questionRepo.findByAtaChapterAndLevelAndEnabledIsTrueAndCheckedIsTrueAndTraining(ataChapter, requirement.getLevel(), exam.getCourse().getTraining());

            // Из-за того что не получается сделать запрос Order by random и Limit в HQL запросе - придется это делать в коде
            Collections.shuffle(questionsPart);
            List<Question> preparedQuestions = questionsPart.stream()
                    .limit(requirement.getQuestionsNumber())
                    .toList();
            examQuestions.addAll(preparedQuestions);

        }
        exam.setQuestions(examQuestions);

        // List<Question> additionalQuestions = questionRepo.findByTrainingAndEnabledIsTrueAndCheckedIsTrueAndAtaChapterInAndNotIn(exam.getCourse().getTraining(), exam.getAtaChapters(), exam.getQuestions());
        // Collections.shuffle(additionalQuestions);
        // List<Question> questionsToAdd = additionalQuestions.stream()
        //             .limit(3)
        //             .toList();
        // System.out.println(questionsToAdd);
        return exam;
    }
}
