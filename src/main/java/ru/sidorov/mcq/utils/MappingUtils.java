package ru.sidorov.mcq.utils;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.ExamDTO;
import ru.sidorov.mcq.model.Exam;

@Service
public class MappingUtils {
    //из entity в dto
    public ExamDTO mapToExamDto(Exam entity){
        ExamDTO dto = new ExamDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setAtaChapters(entity.getAtaChapters());
        dto.setNoteForExaminer(entity.getNoteForExaminer());
        return dto;
    }
    //из dto в entity
//    public ProductEntity mapToProductEntity(ProductDto dto){
//        ProductEntity entity = new ProductEntity();
//        entity.setId(dto.getId());
//        entity.setName(dto.getName());
//        entity.setPurchasePrice(dto.getPurchasePrice());
//        return entity;
//    }
}
