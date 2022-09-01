package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sidorov.mcq.model.Requirements;

public interface RequirementsRepo extends CrudRepository<Requirements, Long> {
}
