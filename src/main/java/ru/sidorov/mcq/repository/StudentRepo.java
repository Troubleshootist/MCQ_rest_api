package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;

import ru.sidorov.mcq.model.Student;

public interface StudentRepo extends CrudRepository<Student, Long>{
    
}
