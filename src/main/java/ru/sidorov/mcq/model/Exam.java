package ru.sidorov.mcq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private String noteForExaminer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="exam_id")
//    private Set<ExamQuestionSequence> questionSequenceSet;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "examquestions",
                joinColumns = {@JoinColumn(name="exam_id")},
                inverseJoinColumns = {@JoinColumn(name="question_id")})
    private List<Question> questions;

    @ManyToMany
    @JoinTable(name="exam_ata_chapters",
                joinColumns = {@JoinColumn(name = "exam_id")},
                inverseJoinColumns = {@JoinColumn(name = "atachapter_id")})
    private Set<AtaChapter> ataChapters;

}
