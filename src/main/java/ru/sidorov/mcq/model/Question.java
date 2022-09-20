package ru.sidorov.mcq.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private Boolean enabled;
    private Boolean checked;
    private String bookPage;
    private Date issueDate;
    private Date checkDate;
    private Date changeDate;
    private long refToOldId;
    private long refToNewId;
    private String issuedBy;
    private String checkedBy;
    private String changedBy;
    private String disableReason;
    private int level;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="question_id")
    private Set<Answer> answers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_chapter_id")
    private AtaChapter ataChapter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "examquestions",
                joinColumns = {@JoinColumn(name = "question_id")},
                inverseJoinColumns = {@JoinColumn(name = "exam_id")})
    private List<Exam> exams;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }


}
