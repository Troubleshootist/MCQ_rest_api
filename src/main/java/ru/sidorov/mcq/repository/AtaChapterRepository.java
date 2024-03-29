package ru.sidorov.mcq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Training;

import java.util.List;

public interface AtaChapterRepository extends JpaRepository<AtaChapter, Long> {
    List<AtaChapter> findByAtaDigitIn(List<String> ataDigits);
    List<AtaChapter> findAllByIdIn(List<String> ataChapterIDList);
    List<AtaChapter> findAllByOrderByAtaDigit();
}
