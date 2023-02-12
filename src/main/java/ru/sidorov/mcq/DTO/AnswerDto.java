package ru.sidorov.mcq.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.Answer} entity
 */
@Data
public class AnswerDto implements Serializable {
    private final long id;
    private final String text;
    private final boolean correct;
}