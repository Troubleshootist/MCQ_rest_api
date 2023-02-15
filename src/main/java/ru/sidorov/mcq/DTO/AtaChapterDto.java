package ru.sidorov.mcq.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.AtaChapter} entity
 */
@Data
public class AtaChapterDto implements Serializable {
    private final long id;
    private final String ataDigit;
    private final String ataDescription;
//    private final List<QuestionDto> questions;
//    private final List<RequirementDto> requirements;
}