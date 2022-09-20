package ru.sidorov.mcq.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter
@Setter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //uuid
    private Date date;
    private String noteForExaminer;

    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    @JoinTable(name = "examquestions",
                joinColumns = {@JoinColumn(name="exam_id")},
                inverseJoinColumns = {@JoinColumn(name="question_id")})
    private List<Question> questions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="exam_ata_chapters",
                joinColumns = {@JoinColumn(name = "exam_id")},
                inverseJoinColumns = {@JoinColumn(name = "atachapter_id")})
    private List<AtaChapter> ataChapters;

    @OneToMany
    @JoinColumn(name = "exam_id")
    @JsonIgnore
    private List<StudentAnswer> answers;

}
