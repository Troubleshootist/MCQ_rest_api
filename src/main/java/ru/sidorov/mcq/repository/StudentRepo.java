package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Student;
@Repository
public interface StudentRepo extends CrudRepository<Student, Long>{
    
}
