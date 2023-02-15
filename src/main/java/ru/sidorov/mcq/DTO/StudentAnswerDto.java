package ru.sidorov.mcq.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.StudentAnswer} entity
 */
@Data
public class StudentAnswerDto implements Serializable {
    private final long id;
    private final ExamDto exam;
    private final QuestionDto question;
    private final boolean correct;
}