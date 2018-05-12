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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_category_id", referencedColumnName = "id")
    private SkillCategory skill;

    @Column(name = "description")
    private String description;

    @Column(name = "video_lesson")
    private boolean videoLesson;

    @Column(name = "live_meeting")
    private boolean liveMeeting;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public boolean isVideoLesson() {
        return videoLesson;
    }

    public void setVideoLesson(boolean videoLesson) {
        this.videoLesson = videoLesson;
    }

    public boolean isLiveMeeting() {
        return liveMeeting;
    }

    public void setLiveMeeting(boolean liveMeeting) {
        this.liveMeeting = liveMeeting;
    }
}
