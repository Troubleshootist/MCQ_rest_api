package ru.sidorov.mcq.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * A DTO for the {@link ru.sidorov.mcq.model.Student} entity
 */
@Data
@NoArgsConstructor()
public class StudentDto implements Serializable {
    private long id;
    private String name;
    private String surname;
    private Date dob;
    private double courseStats;
}