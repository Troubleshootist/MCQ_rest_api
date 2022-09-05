package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Requirement;
import ru.sidorov.mcq.model.Training;

public interface RequirementRepo extends CrudRepository<Requirement, Long> {
    Requirement findByTrainingAndAtaChapter(Training training, AtaChapter ataChapter);
}
