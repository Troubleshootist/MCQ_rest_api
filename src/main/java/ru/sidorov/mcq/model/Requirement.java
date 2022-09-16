package ru.sidorov.mcq.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int questionsNumber;
    private int level;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_id")
    private AtaChapter ataChapter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="training_id")
    private Training training;

    @Override
    public String toString() {
        return String.format("Training: %s, questions number: %d", training.getName(), questionsNumber);
    }

}
