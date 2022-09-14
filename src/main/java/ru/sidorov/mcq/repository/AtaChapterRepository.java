package ru.sidorov.mcq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sidorov.mcq.model.AtaChapter;

import java.util.List;

public interface AtaChapterRepository extends JpaRepository<AtaChapter, Long> {
    List<AtaChapter> findByAtaDigitIn(List<String> ataDigits);
}
