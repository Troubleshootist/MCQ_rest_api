package ru.sidorov.mcq.utils.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.StudentAnswerDto;
import ru.sidorov.mcq.model.StudentAnswer;

@Service
public class StudentAnswerMapper {
    private ExamMapper examMapper;
    private QuestionMapper questionMapper;

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    public StudentAnswerDto mapStudentAnswerToDto(StudentAnswer entity) {
        StudentAnswerDto studentAnswerDto = new StudentAnswerDto(
                entity.getId(),
                examMapper.mapToExamDto(entity.getExam()),
                questionMapper.mapToQuestionDtoDto(entity.getQuestion()),
                entity.isCorrect()
        );
        return studentAnswerDto;
    }
}
