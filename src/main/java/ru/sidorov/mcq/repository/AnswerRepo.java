package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sidorov.mcq.model.Answer;

public interface AnswerRepo extends CrudRepository<Answer, Long> {

}
