package ru.sidorov.mcq.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.Requirement} entity
 */
@Data
public class RequirementDto implements Serializable {
    private final long id;
    private final int questionsNumber;
    private final int level;
}