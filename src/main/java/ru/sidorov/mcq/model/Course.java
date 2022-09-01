package ru.sidorov.mcq.model;

import javax.persistence.*;

@Entity
public class Course {
    private long id;
    private String courseNumber;

    private Training training;

    public Course() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
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
