package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Question;


import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long> {
    Iterable<Question> findByEnabledAndChecked(boolean enabled, boolean checked);
    Iterable<Question> findByEnabled(boolean enabled);

    Iterable<Question> findByAtaChapterIn(Set<AtaChapter> ataChapters);

    List<Question> findByAtaChapterAndLevel(AtaChapter ataChapter, int level);

//    @Query("select q from Question q where ataChapter = :ataChapter and q.level = :level order by RAND(123)")
//    Iterable<Question> findByAtaChapterAndLevelLimited(@Param("ataChapter") AtaChapter ataChapter,
//                                                       @Param("level") int level);
}
