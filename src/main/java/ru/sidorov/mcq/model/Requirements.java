package ru.sidorov.mcq.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Requirements {
    private long id;
    private int questionsNumber;
    private int level;

    private AtaChapter ataChapter;
    private Training training;

    public Requirements() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(int questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_id")
    public AtaChapter getAtaChapter() {
        return ataChapter;
    }

    public void setAtaChapter(AtaChapter ataChapter) {
        this.ataChapter = ataChapter;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="training_id")
    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
