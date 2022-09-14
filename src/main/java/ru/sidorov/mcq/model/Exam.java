package ru.sidorov.mcq.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity 
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {})
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
    @JoinTable(name = "examquestions",
                joinColumns = {@JoinColumn(name="exam_id")},
                inverseJoinColumns = {@JoinColumn(name="question_id")})
    private List<Question> questions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="exam_ata_chapters",
                joinColumns = {@JoinColumn(name = "exam_id")},
                inverseJoinColumns = {@JoinColumn(name = "atachapter_id")})
    private List<AtaChapter> ataChapters;

}
