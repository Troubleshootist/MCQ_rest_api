package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Training;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long> {
    Iterable<Question> findByEnabledAndChecked(boolean enabled, boolean checked);
    Iterable<Question> findByEnabled(boolean enabled);

    Iterable<Question> findByAtaChapterIn(Set<AtaChapter> ataChapters);

    List<Question> findByAtaChapterAndLevelAndEnabledIsTrueAndCheckedIsTrueAndTraining(AtaChapter ataChapter, int level, Training training);

    // List<Question> findByTrainingAndEnabledIsTrueAndCheckedIsTrueAndAtaChapterInAndNotIn(Training training, Set<AtaChapter> ataChapters, List<Question> restrictedQuestions);
    
}
