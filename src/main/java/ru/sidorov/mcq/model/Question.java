package ru.sidorov.mcq.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="question_id")
    private Set<Answer> answers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_chapter_id")
    private AtaChapter ataChapter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Training training;

}
