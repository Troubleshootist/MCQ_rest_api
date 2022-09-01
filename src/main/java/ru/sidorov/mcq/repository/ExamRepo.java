package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sidorov.mcq.model.Exam;

public interface ExamRepo extends CrudRepository<Exam, Long> {
}
