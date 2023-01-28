package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.exceptions.MyEntityNotFoundException;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.repository.ExamRepo;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.services.exam_service_helpers.AddAdditionalQuestionsToDivideByFourHelper;
import ru.sidorov.mcq.services.exam_service_helpers.AddMinimumQuestionsHelper;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExamService {

    private StudentAnswerRepo studentAnswerRepo;
    private ExamRepo examRepo;

    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
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

    public Map<Student, Double> getPercentageByStudents(Exam exam) {
        Map<Student, Double> studentStats = new HashMap<>();
        for (Student student: exam.getCourse().getStudents()) {
            int studentCorrectAnswersCount = studentAnswerRepo.countStudentAnswerByStudentAndExamAndCorrect(student, exam, true);
            int studentIncorrectAnswersCount = studentAnswerRepo.countStudentAnswerByStudentAndExamAndCorrect(student, exam, false);
            int totalStudentAnswers = studentCorrectAnswersCount + studentIncorrectAnswersCount;
            double correctAnswersPercentage = (double) studentCorrectAnswersCount/(double) totalStudentAnswers * 100;
            studentStats.put(student, correctAnswersPercentage);
        }
        return studentStats;
    }

    public Exam createReexam(Exam exam, long initialExamId) {
        Exam initialExam = examRepo.findById(initialExamId).orElseThrow(() -> new MyEntityNotFoundException("No initial examID found"));
        exam.setAtaChapters(initialExam.getAtaChapters());
        exam.setCourse(initialExam.getCourse());
        return null; // TODO: написать хелпер по созданию реэкзамена
    }
}
