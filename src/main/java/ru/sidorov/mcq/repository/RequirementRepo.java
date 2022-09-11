package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Requirement;
import ru.sidorov.mcq.model.Training;
@Repository
public interface RequirementRepo extends CrudRepository<Requirement, Long> {
    Requirement findByTrainingAndAtaChapter(Training training, AtaChapter ataChapter);
}
