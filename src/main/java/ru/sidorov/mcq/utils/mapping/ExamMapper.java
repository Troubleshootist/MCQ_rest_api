package ru.sidorov.mcq.utils.mapping;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.ExamDto;
import ru.sidorov.mcq.model.Exam;

@Service
public class ExamMapper {

    public ExamDto mapToExamDto(Exam entity){
        ExamDto dto = new ExamDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setAtaChapters(entity.getAtaChapters());
        dto.setNoteForExaminer(entity.getNoteForExaminer());
        return dto;
    }
}
