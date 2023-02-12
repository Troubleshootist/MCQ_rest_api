package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.ExamDto;
import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Course;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.services.exam_service_helpers.AddAdditionalQuestionsToDivideByFourHelper;
import ru.sidorov.mcq.services.exam_service_helpers.AddMinimumQuestionsHelper;
import ru.sidorov.mcq.utils.MappingUtils;
import ru.sidorov.mcq.utils.mapping.ExamMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamService {

    private StudentAnswerRepo studentAnswerRepo;
    private ExamRepo examRepo;
    private ExamMapper examMapper;

    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    @Autowired
    public void setExamRepo(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    private AddMinimumQuestionsHelper addMinimumQuestionsHelper;
    private AddAdditionalQuestionsToDivideByFourHelper addAdditionalQuestionsToDivideByFourHelper;

    @Autowired
    public void setAddMinimumQuestionsHelper(AddMinimumQuestionsHelper addMinimumQuestionsHelper) {
        this.addMinimumQuestionsHelper = addMinimumQuestionsHelper;
    }
    @Autowired
    public void setAddAdditionalQuestionsToDivideByFour(AddAdditionalQuestionsToDivideByFourHelper addAdditionalQuestionsToDivideByFourHelper) {
        this.addAdditionalQuestionsToDivideByFourHelper = addAdditionalQuestionsToDivideByFourHelper;
    }

    public Exam createExam(Exam exam) {
        // Добавляем вопросы в соответствии с requirements
        addMinimumQuestionsHelper.addMinimumQuestions(exam);

        // Добавляем доп вопросы, чтобы кол-во делилось на 4
        addAdditionalQuestionsToDivideByFourHelper.addAdditionalQuestionsToDivideByFour(exam);
        return exam;
    }

    public List<ExamDto> getCorrectPercentageInCourse(Course course) {
        List<ExamDto> examDtoList = new ArrayList<>();
        for (Exam exam :
                course.getExams()) {
            ExamDto examDTO = examMapper.mapToExamDto(exam);
            examDTO.setCorrectAnswersPercentage(getCorrectPercentage(exam));
            examDtoList.add(examDTO);
        }
        return examDtoList;
    }

    public Double getCorrectPercentage(Exam exam) {
        int examCorrectAnswersCount = studentAnswerRepo.countStudentAnswerByExamAndCorrect(exam, true);
        int examIncorrectAnswersCount = studentAnswerRepo.countStudentAnswerByExamAndCorrect(exam, false);
        int totalExamAnswers = examIncorrectAnswersCount + examCorrectAnswersCount;
        return (double)examCorrectAnswersCount/(double)totalExamAnswers*100;
    }

    public Map<Question, Double> getPercentageByQuestions(Exam exam) {
        Map<Question, Double> questionStats = new HashMap<>();
        for (Question question: exam.getQuestions()) {
            int questionCorrectAnswersCount = studentAnswerRepo.countStudentAnswerByExamAndQuestionAndCorrect(exam, question, true);
            int questionIncorrectAnswersCount = studentAnswerRepo.countStudentAnswerByExamAndQuestionAndCorrect(exam, question, false);
            int totalQuestionAnswersCount = questionCorrectAnswersCount + questionIncorrectAnswersCount;
            double correctAnswersPercentage = (double) questionCorrectAnswersCount/(double) totalQuestionAnswersCount * 100;

            questionStats.put(question, correctAnswersPercentage);

        }
        return questionStats;
    }



    public Exam createReexam(Exam exam, long initialExamId) {
        Exam initialExam = examRepo.findById(initialExamId).orElseThrow(() -> new MyEntityNotFoundException("No initial examID found"));
        exam.setAtaChapters(initialExam.getAtaChapters());
        exam.setCourse(initialExam.getCourse());
        // TODO: здесь булет код для реекзамена
        return null;
    }

}
