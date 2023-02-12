package ru.sidorov.mcq.utils.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.QuestionDto;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.services.QuestionService;

@Service
public class QuestionMapper {

    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

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
        dto.setTotalCorrectAnswersPercentage(questionService.getCorrectPercentage(entity));
        dto.setExamsList(questionService.getExams(entity));
        dto.setAnswersList(questionService.getAnswers(entity));

        return dto;
    }
}
