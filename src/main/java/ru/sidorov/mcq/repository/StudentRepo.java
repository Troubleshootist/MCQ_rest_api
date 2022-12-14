package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.model.StudentAnswer;

import java.util.List;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long>{
    List<Student> findByAnswersIn(List<StudentAnswer> examStudentAnswers);
}
