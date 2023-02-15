package ru.sidorov.mcq.DTO;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.Question} entity
 */
@Data
public class QuestionDto implements Serializable {
    private final long id;
    private final String text;
    private final Boolean enabled;
    private final Boolean checked;
    private final String bookPage;
    private final Date issueDate;
    private final Date checkDate;
    private final Date changeDate;
    private final long refToOldId;
    private final long refToNewId;
    private final String issuedBy;
    private final String checkedBy;
    private final String changedBy;
    private final String disableReason;
    private final int level;
    private double totalCorrectAnswersPercentage;
    private List<ExamDto> examsList;
    private List<AnswerDto> answersList;
    private TrainingDto training;
}