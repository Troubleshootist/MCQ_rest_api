package ru.sidorov.mcq.utils.mapping;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.QuestionDto;
import ru.sidorov.mcq.model.Question;

@Service
public class QuestionMapper {

    public QuestionDto mapToQuestionDtoDto(Question entity){
        QuestionDto dto = new QuestionDto(
                entity.getId(),
                entity.getText(),
                entity.getEnabled(),
                entity.getChecked(),
                entity.getBookPage(),
                entity.getIssueDate(),
                entity.getCheckDate(),
                entity.getChangeDate(),
                entity.getRefToOldId(),
                entity.getRefToNewId(),
                entity.getIssuedBy(),
                entity.getCheckedBy(),
                entity.getChangedBy(),
                entity.getDisableReason(),
                entity.getLevel()
        );


        return dto;
    }
}
