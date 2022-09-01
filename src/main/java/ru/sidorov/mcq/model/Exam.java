package ru.sidorov.mcq.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Exam {
    private long id;
    private Date date;
    private String noteForExaminer;

    private Course course;
    private Set<ExamQuestionSequence> questionSequenceSet;

    public Exam() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNoteForExaminer() {
        return noteForExaminer;
    }

    public void setNoteForExaminer(String noteForExaminer) {
        this.noteForExaminer = noteForExaminer;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="exam_id")
    public Set<ExamQuestionSequence> getQuestionSequenceSet() {
        return questionSequenceSet;
    }

    public void setQuestionSequenceSet(Set<ExamQuestionSequence> questionSequenceSet) {
        this.questionSequenceSet = questionSequenceSet;
    }
}
