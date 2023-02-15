package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.StudentAnswerDto;
import ru.sidorov.mcq.model.StudentAnswer;
import ru.sidorov.mcq.repository.StudentAnswerRepo;
import ru.sidorov.mcq.utils.mapping.StudentAnswerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentAnswerService {
    private StudentAnswerMapper studentAnswerMapper;

    @Autowired
    public void setStudentAnswerMapper(StudentAnswerMapper studentAnswerMapper) {
        this.studentAnswerMapper = studentAnswerMapper;
    }



    public List<StudentAnswerDto> mapAnswersListToDtoList(List<StudentAnswer> studentAnswers) {
        return studentAnswers.stream()
                .map(a -> studentAnswerMapper.mapStudentAnswerToDto(a))
                .collect(Collectors.toList());
    }
}
