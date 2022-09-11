package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Training;
@Repository
public interface TrainingRepo extends CrudRepository<Training, Long> {
}
