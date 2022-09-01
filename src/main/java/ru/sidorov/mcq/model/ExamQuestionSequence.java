package ru.sidorov.mcq.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ExamQuestionSequence {
    private long id;
    private int sequenceNumber;

    private Exam exam;
    private Question question;

    public ExamQuestionSequence() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    public Exam getExam() {
        return exam;
    }


    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
