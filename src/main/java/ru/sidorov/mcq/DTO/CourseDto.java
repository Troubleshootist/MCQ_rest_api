package ru.sidorov.mcq.DTO;

import ru.sidorov.mcq.model.Training;

import java.util.List;

public class CourseDto {
    private long id;
    private Training training;
    private List<ExamDto> examDtoList;
    private List<StudentDto> studentDtoList;
}
