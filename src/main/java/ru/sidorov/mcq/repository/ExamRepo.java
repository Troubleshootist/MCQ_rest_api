package ru.sidorov.mcq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Question;

import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
}
