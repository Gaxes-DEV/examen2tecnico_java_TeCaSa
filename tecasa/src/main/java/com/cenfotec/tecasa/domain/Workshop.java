package com.cenfotec.tecasa.domain;

import javax.persistence.*;
import java.text.ParseException;

import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "TWorkshop")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "objective")
    private String objective;

    @Column(name = "category")
    private String category;

    @Column(name = "keywords")
    private String keyWords;

    @Column(name = "duration")
    private String executionTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workshop")
    private Set<Activity> activities;

    public Workshop() {
    }

    public Workshop(Long id, String name, String author, String objective, String category, String keyWords, String executionTime) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.objective = objective;
        this.category = category;
        this.keyWords = keyWords;
        this.executionTime = executionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
