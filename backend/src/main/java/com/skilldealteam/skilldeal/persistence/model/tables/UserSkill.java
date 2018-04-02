package com.skilldealteam.skilldeal.persistence.model.tables;

import com.skilldealteam.skilldeal.persistence.model.BaseModel;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_skill")
public class UserSkill extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "lesson_price")
    private Integer lessonPrice;

    @JoinColumn(name = "skill_category_id", referencedColumnName = "id")
    private SkillCategory skill;

    @OneToMany(mappedBy = "user_skill_id")
    private Set<Tag> tags;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Integer lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public SkillCategory getSkill() {
        return skill;
    }

    public void setSkill(SkillCategory skill) {
        this.skill = skill;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
