package com.skilldealteam.skilldeal.persistence.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilldealteam.skilldeal.persistence.model.BaseModel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "skill_category")
public class SkillCategory extends BaseModel{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    public SkillCategory() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
