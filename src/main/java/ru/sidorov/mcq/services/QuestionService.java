package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.repository.StudentAnswerRepo;

@Service
public class QuestionService {
    private StudentAnswerRepo studentAnswerRepo;
    private QuestionRepo questionRepo;
    private ExamRepo examRepo;


    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }


    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    @Autowired
    public void setExamRepo(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    public double getCorrectPercentage(Question question) {
        int correctQuestionAnswersCount = studentAnswerRepo.countStudentAnswerByQuestionAndCorrect(question, true);
        int incorrectQuestionAnswersCount = studentAnswerRepo.countStudentAnswerByQuestionAndCorrect(question, false);
        int totalQuestionAnswerCount = correctQuestionAnswersCount + incorrectQuestionAnswersCount;
        return (double) correctQuestionAnswersCount/(double) totalQuestionAnswerCount * 100;
    }

    public int usedTimes(Question question) {
        return questionRepo.findByExamsInAndId(examRepo.findAll(), question.getId()).size();
    }

}
