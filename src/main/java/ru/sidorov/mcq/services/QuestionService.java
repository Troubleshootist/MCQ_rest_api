package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.AnswerDto;
import ru.sidorov.mcq.DTO.ExamDto;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.QuestionRepo;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.utils.mapping.AnswerMapper;
import ru.sidorov.mcq.utils.mapping.ExamMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private StudentAnswerRepo studentAnswerRepo;
    private QuestionRepo questionRepo;
    private ExamRepo examRepo;
    private AnswerMapper answerMapper;
    private ExamMapper examMapper;

    @Autowired
    public void setAnswerMapper(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }
    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }


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

    public List<ExamDto> getExams(Question question) {
        return question.getExams()
                .stream()
                .map(exam -> examMapper.mapToExamDto(exam))
                .collect(Collectors.toList());
    }

    public List<AnswerDto> getAnswers(Question question) {
        return question.getAnswers()
                .stream()
                .map(answer -> answerMapper.mapToAnswerDto(answer))
                .collect(Collectors.toList());
    }
}
