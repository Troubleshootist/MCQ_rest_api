package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sidorov.mcq.model.Training;

public interface TrainingRepo extends CrudRepository<Training, Long> {
}
