package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sidorov.mcq.model.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long> {
    Iterable<Question> findQuestionsByEnabledAndChecked(boolean enabled, boolean checked);
    Iterable<Question> findQuestionsByEnabled(boolean enabled);
}
