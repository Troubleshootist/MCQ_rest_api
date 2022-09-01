package ru.sidorov.mcq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Training {
    private long id;
    private String name;

    private Set<Question> questions;
    private Set<Requirements> requirements;
    private Set<Course> courses;

    public Training() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="training_id")
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    public Set<Requirements> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirements> requirements) {
        this.requirements = requirements;
    }
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
