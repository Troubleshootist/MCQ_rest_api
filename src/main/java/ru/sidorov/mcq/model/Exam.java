package ru.sidorov.mcq.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @ManyToMany
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
