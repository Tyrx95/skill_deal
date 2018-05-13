package com.skilldealteam.skilldeal.persistence.model.tables;

import com.skilldealteam.skilldeal.persistence.model.BaseModel;

import javax.persistence.*;
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

    @ManyToOne()
    @JoinColumn(name = "skill_category_id", referencedColumnName = "id")
    private SkillCategory skill;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "gives_video_lesson")
    private Boolean givesVideoLesson;

    @Column(name = "gives_live_meeting")
    private Boolean givesLiveMeeting;

    public UserSkill() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getGivesVideoLesson() {
        return givesVideoLesson;
    }

    public void setGivesVideoLesson(Boolean givesVideoLesson) {
        this.givesVideoLesson = givesVideoLesson;
    }

    public Boolean getGivesLiveMeeting() {
        return givesLiveMeeting;
    }

    public void setGivesLiveMeeting(Boolean givesLiveMeeting) {
        this.givesLiveMeeting = givesLiveMeeting;
    }
}
