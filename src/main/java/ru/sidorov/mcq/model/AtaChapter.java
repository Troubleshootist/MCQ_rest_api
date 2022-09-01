package ru.sidorov.mcq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="atachapter", schema = "public")
public class AtaChapter {
    private long id;
    private String ataDigit;
    private String ataDescription;

    private Set<Question> questions;
    private Set<Requirements> requirements;

    public AtaChapter() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAtaDigit() {
        return ataDigit;
    }

    public void setAtaDigit(String ataDigit) {
        this.ataDigit = ataDigit;
    }

    public String getAtaDescription() {
        return ataDescription;
    }

    public void setAtaDescription(String ataDescription) {
        this.ataDescription = ataDescription;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_chapter_id")
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_id")
    public Set<Requirements> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirements> requirements) {
        this.requirements = requirements;
    }
}
