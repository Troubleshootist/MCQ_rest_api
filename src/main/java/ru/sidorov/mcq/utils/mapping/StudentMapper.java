package ru.sidorov.mcq.utils.mapping;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.StudentDto;
import ru.sidorov.mcq.model.Student;


@Service
public class StudentMapper {

    public StudentDto mapToStudentDto(Student entity){
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setDob(entity.getDob());
        return dto;
    }
}
