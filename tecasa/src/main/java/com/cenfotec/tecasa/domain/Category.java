package com.cenfotec.tecasa.domain;

import javax.persistence.*;

@Entity
@Table(name = "TCategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String cName;

    @Column(name = "status")
    private String status;

    public Category() {
    }

    public Category(Long id, String cName, String status) {
        this.id = id;
        this.cName = cName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
