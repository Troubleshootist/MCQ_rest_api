package ru.sidorov.mcq.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Question {


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

    private Set<Answer> answers;
    private AtaChapter ataChapter;
    private Training training;

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public long getRefToOldId() {
        return refToOldId;
    }

    public void setRefToOldId(long refToOldId) {
        this.refToOldId = refToOldId;
    }

    public long getRefToNewId() {
        return refToNewId;
    }

    public void setRefToNewId(long refToNewId) {
        this.refToNewId = refToNewId;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

//    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="question_id")
    public Set<Answer> getAnswers() {
        return answers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getBookPage() {
        return bookPage;
    }

    public void setBookPage(String bookPage) {
        this.bookPage = bookPage;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_chapter_id")
    public AtaChapter getAtaChapter() {
        return ataChapter;
    }

    public void setAtaChapter(AtaChapter ataChapter) {
        this.ataChapter = ataChapter;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
