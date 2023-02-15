package ru.sidorov.mcq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = true)
    private boolean correct;
}
