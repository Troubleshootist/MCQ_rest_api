package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sidorov.mcq.model.Question;

public interface QuestionRepo extends CrudRepository<Question, Long> {
    Iterable<Question> findQuestionsByEnabledAndChecked(boolean enabled, boolean checked);
    Iterable<Question> findQuestionsByEnabled(boolean enabled);
}
