package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Exam;
@Repository
public interface ExamRepo extends CrudRepository<Exam, Long> {
}
