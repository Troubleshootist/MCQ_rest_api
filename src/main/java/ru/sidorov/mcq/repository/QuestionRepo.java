package ru.sidorov.mcq.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Training;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long>, JpaSpecificationExecutor<Question> {
    Iterable<Question> findByEnabledAndChecked(boolean enabled, boolean checked);
    Iterable<Question> findByEnabled(boolean enabled);

    Iterable<Question> findByAtaChapterIn(Set<AtaChapter> ataChapters);

    List<Question> findByExamsInAndId(List<Exam> exams, long id);
    // criteria builder, pageable! сделать спецификацию обьектом и вызывать статические методы
    List<Question> findByAtaChapterAndLevelAndEnabledIsTrueAndCheckedIsTrueAndTraining(AtaChapter ataChapter, int level, Training training);
    List<Question> findByTrainingAndEnabledIsTrueAndCheckedIsTrueAndAtaChapterInAndIdNotIn(Training training, List<AtaChapter> ataChapters, List<Long> restrictedQuestionsIdList);
    List<Question> findByAtaChapterInAndTraining(List<AtaChapter> ataChapters, Training training);
}
