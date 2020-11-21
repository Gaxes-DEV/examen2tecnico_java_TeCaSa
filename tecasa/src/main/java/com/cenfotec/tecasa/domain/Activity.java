package com.cenfotec.tecasa.domain;

import javax.persistence.*;

@Entity
@Table(name = "TActivity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String aName;

    @Column(name = "description")
    private String description;

    @Column(name = "public_text")
    private String visibleText;

    @Column(name = "duration")
    private String aDuration;

    @ManyToOne
    @JoinColumn(name = "workshop_id", nullable = false)
    private Workshop workshop;

    public Activity() {
    }

    public Activity(Long id, String aName, String description, String visibleText, String aDuration, Workshop workshop) {
        this.id = id;
        this.aName = aName;
        this.description = description;
        this.visibleText = visibleText;
        this.aDuration = aDuration;
        this.workshop = workshop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisibleText() {
        return visibleText;
    }

    public void setVisibleText(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getaDuration() {
        return aDuration;
    }

    public void setaDuration(String aDuration) {
        this.aDuration = aDuration;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
}
