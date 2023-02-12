package ru.sidorov.mcq.DTO;

import lombok.Data;
import ru.sidorov.mcq.model.StudentAnswer;

import java.sql.Date;
import java.util.List;

@Data
public class StudentDto {
    private long id;
    private String name;
    private String surname;
    private Date dob;
    private List<StudentAnswer> answers;
    private double totalCorrectPercentage;
}
