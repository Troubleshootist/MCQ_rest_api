package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.model.StudentAnswer;

import java.util.List;

@Repository
public interface StudentAnswerRepo extends CrudRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByExamAndStudent(Exam exam, Student student);
    List<StudentAnswer> findByExam(Exam exam);
    int countStudentAnswerByExamAndCorrect(Exam exam, boolean correct);
    int countStudentAnswerByExamAndQuestionAndCorrect(Exam exam, Question question, boolean correct);
    int countStudentAnswerByStudentAndExamAndCorrect(Student student, Exam exam, boolean correct);
    List<StudentAnswer> findByQuestion(Question question);
    int countStudentAnswerByQuestionAndCorrect(Question question, boolean correct);
}
