package ru.sidorov.mcq.utils.mapping;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.Answer;
import ru.sidorov.mcq.DTO.AnswerDto;


@Service
public class AnswerMapper {
    public AnswerDto mapToAnswerDto(Answer entity){
        AnswerDto dto = new AnswerDto(
                entity.getId(),
                entity.getText(),
                entity.isCorrect()
        );

        return dto;
    }
}
