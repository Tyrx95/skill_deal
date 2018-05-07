package com.skilldealteam.skilldeal.persistence.model.tables;

import com.skilldealteam.skilldeal.persistence.model.BaseModel;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "skill", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserSkill userSkill;

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

    public void setUserSkill(UserSkill userSkill) {
        if (userSkill == null) {
            if (this.userSkill != null) {
                this.userSkill.setSkill(null);
            }
        }
        else {
            userSkill.setSkill(this);
        }
        this.userSkill = userSkill;
    }
}
