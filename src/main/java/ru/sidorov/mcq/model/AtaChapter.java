package ru.sidorov.mcq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="atachapter", schema = "public")
public class AtaChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ataDigit;
    private String ataDescription;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ata_chapter_id")
    private Set<Question> questions;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="ata_id")
    private Set<Requirement> requirements;
    @Override
    public String toString() {
        return "AtaChapter [ATA=" + ataDigit + ", Description=" + ataDescription + "]";
    }

    



}
