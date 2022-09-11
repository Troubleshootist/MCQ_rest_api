package ru.sidorov.mcq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorov.mcq.model.Exam;
import ru.sidorov.mcq.services.examhelpers.AddAdditionalQuestionsToDivideByFourHelper;
import ru.sidorov.mcq.services.examhelpers.AddMinimumQuestionsHelper;

@Service
public class ExamService {

    private AddMinimumQuestionsHelper addMinimumQuestionsHelper;
    private AddAdditionalQuestionsToDivideByFourHelper addAdditionalQuestionsToDivideByFourHelper;

    @Autowired
    public void setAddMinimumQuestionsHelper(AddMinimumQuestionsHelper addMinimumQuestionsHelper) {
        this.addMinimumQuestionsHelper = addMinimumQuestionsHelper;
    }
    @Autowired
    public void setAddAdditionalQuestionsToDivideByFour(AddAdditionalQuestionsToDivideByFourHelper addAdditionalQuestionsToDivideByFourHelper) {
        this.addAdditionalQuestionsToDivideByFourHelper = addAdditionalQuestionsToDivideByFourHelper;
    }

    public Exam createExam(Exam exam) {
        // Добавляем вопросы в соответствии с requirements
        addMinimumQuestionsHelper.addMinimumQuestions(exam);

        // Добавляем доп вопросы, чтобы кол-во делилось на 4
        addAdditionalQuestionsToDivideByFourHelper.addAdditionalQuestionsToDivideByFour(exam);
        return exam;
    }

    

}
