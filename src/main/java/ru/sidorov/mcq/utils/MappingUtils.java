package ru.sidorov.mcq.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.ExamDto;
import ru.sidorov.mcq.DTO.StudentDto;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.model.Student;
import ru.sidorov.mcq.services.StudentService;

@Service
public class MappingUtils {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    //из entity в dto

    //из dto в entity
//    public ProductEntity mapToProductEntity(ProductDto dto){
//        ProductEntity entity = new ProductEntity();
//        entity.setId(dto.getId());
//        entity.setName(dto.getName());
//        entity.setPurchasePrice(dto.getPurchasePrice());
//        return entity;
//    }
    public StudentDto mapToStudentDto(Student entity){
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setDob(entity.getDob());
        dto.setAnswers(entity.getAnswers());
        dto.setTotalCorrectPercentage(studentService.getStudentTotalCorrectPercentage(entity));
        return dto;
    }
}
