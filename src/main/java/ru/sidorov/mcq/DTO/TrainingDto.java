package ru.sidorov.mcq.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.Training} entity
 */
@Data
public class TrainingDto implements Serializable {
    private final String name;
    private final List<QuestionDto> questions;
    private final List<RequirementDto> requirements;
    private final List<CourseDto> courses;
}