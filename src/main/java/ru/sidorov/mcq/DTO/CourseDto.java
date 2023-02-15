package ru.sidorov.mcq.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.Course} entity
 */
@Data
@NoArgsConstructor
public class CourseDto implements Serializable {
    private long id;
    private String courseNumber;
    private TrainingDto training;
    private List<ExamDto> exams;
    private List<StudentDto> students;
    private double correctAnswersPercentage;
}