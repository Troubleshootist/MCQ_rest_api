package ru.sidorov.mcq.repository;

import org.springframework.data.repository.CrudRepository;

import ru.sidorov.mcq.model.Course;

public interface CourseRepo extends CrudRepository<Course, Long>{
    
}
