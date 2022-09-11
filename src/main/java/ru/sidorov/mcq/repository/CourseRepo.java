package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Course;
@Repository
public interface CourseRepo extends CrudRepository<Course, Long>{
    
}
