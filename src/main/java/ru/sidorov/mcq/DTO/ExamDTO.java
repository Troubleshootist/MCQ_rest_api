package ru.sidorov.mcq.DTO;

import lombok.Data;
import ru.sidorov.mcq.model.AtaChapter;

import java.sql.Date;
import java.util.List;

@Data
public class ExamDTO {
    private long id;
    private Date date;
    private String noteForExaminer;
    private double CorrectAnswersPercentage;
    private List<AtaChapter> ataChapters;
}
