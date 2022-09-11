package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Answer;
@Repository
public interface AnswerRepo extends CrudRepository<Answer, Long> {

}
