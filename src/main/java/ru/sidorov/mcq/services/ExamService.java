package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Requirement;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.repository.RequirementRepo;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExamService {
    private Exam exam;
    private ExamRepo examRepo;
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

    public ExamService() {

    }

    public Exam createExam(Exam exam) {
        List<Question> examQuestions = new ArrayList<>();
        for (AtaChapter ataChapter: exam.getAtaChapters()) {
            Requirement requirement = requirementRepo.findByTrainingAndAtaChapter(exam.getCourse().getTraining(), ataChapter);
            List<Question> questionsPart = questionRepo.findByAtaChapterAndLevel(ataChapter, requirement.getLevel());

            // Из-за того что не получается сделать запрос Order by random и Limit в HQL запросе - придется это делать в коде
            Collections.shuffle(questionsPart);
            List<Question> preparedQuestions = questionsPart.stream()
                    .limit(requirement.getQuestionsNumber())
                    .toList();
            examQuestions.addAll(preparedQuestions);

        }
        exam.setQuestions(examQuestions);
        return exam;
    }
}
