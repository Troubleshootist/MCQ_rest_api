package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.*;
import ru.sidorov.mcq.model.AtaChapter;
import ru.sidorov.mcq.model.Question;
import ru.sidorov.mcq.model.Training;
import ru.sidorov.mcq.repository.*;
import ru.sidorov.mcq.utils.mapping.AnswerMapper;
import ru.sidorov.mcq.utils.mapping.ExamMapper;
import ru.sidorov.mcq.utils.mapping.QuestionMapper;
import ru.sidorov.mcq.utils.mapping.TrainingMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private StudentAnswerRepo studentAnswerRepo;
    private QuestionRepo questionRepo;
    private QuestionMapper questionMapper;
    private ExamRepo examRepo;
    private AnswerMapper answerMapper;
    private ExamMapper examMapper;
    private TrainingRepo trainingRepo;
    private AtaChapterRepository ataChapterRepository;
    private TrainingMapper trainingMapper;

    @Autowired
    public void setTrainingMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }

    @Autowired
    public void setAnswerMapper(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }
    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }


    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Autowired
    public void setTrainingRepo(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    @Autowired
    public void setAtaChapterRepository(AtaChapterRepository ataChapterRepository) {
        this.ataChapterRepository = ataChapterRepository;
    }

    @Autowired
    public void setStudentAnswerRepo(StudentAnswerRepo studentAnswerRepo) {
        this.studentAnswerRepo = studentAnswerRepo;
    }

    @Autowired
    public void setExamRepo(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    public double getCorrectPercentage(Question question) {
        int correctQuestionAnswersCount = studentAnswerRepo.countStudentAnswerByQuestionAndCorrect(question, true);
        int incorrectQuestionAnswersCount = studentAnswerRepo.countStudentAnswerByQuestionAndCorrect(question, false);
        int totalQuestionAnswerCount = correctQuestionAnswersCount + incorrectQuestionAnswersCount;
        return (double) correctQuestionAnswersCount/(double) totalQuestionAnswerCount * 100;
    }


    public List<ExamDto> getExams(Question question) {
        return question.getExams()
                .stream()
                .map(exam -> examMapper.mapToExamDto(exam))
                .collect(Collectors.toList());
    }

    public List<AnswerDto> getAnswers(Question question) {
        return question.getAnswers()
                .stream()
                .map(answer -> answerMapper.mapToAnswerDto(answer))
                .collect(Collectors.toList());
    }



    public Iterable<QuestionDto> findAllByAtaChapterInAndTrainingID(List<String> checkedAtaDigitList, long trainingID) {
        Training trainingDAO = trainingRepo.findById(trainingID).orElseThrow();
        List<AtaChapter> ataChaptersDAO = ataChapterRepository.findByAtaDigitIn(checkedAtaDigitList);
        List<Question> filteredQuestions = questionRepo.findAllByAtaChapterInAndTraining(ataChaptersDAO, trainingDAO);
        return filteredQuestions
                .stream()
                .map(question -> {
                    QuestionDto questionDto = questionMapper.mapToQuestionDtoDto(question);
                    setAdditionalInfoToQuestionDto(questionDto, question);
                    return questionDto;
                })
                .collect(Collectors.toList());
    }
    public QuestionDto setAdditionalInfoToQuestionDto(QuestionDto questionDto, Question question) {
        questionDto.setTotalCorrectAnswersPercentage(getCorrectPercentage(question));
        questionDto.setAnswersList(getAnswers(question));
        questionDto.setExamsList(getExams(question));
        questionDto.setTraining(trainingMapper.mapToTrainingDto(question.getTraining()));
        return questionDto;
    }

    public QuestionDto findById(long id) {
        Question questionDAO = questionRepo.findById(id).orElseThrow();
        QuestionDto questionDto = questionMapper.mapToQuestionDtoDto(questionDAO);
        setAdditionalInfoToQuestionDto(questionDto, questionDAO);
        return questionDto;
    }
}
