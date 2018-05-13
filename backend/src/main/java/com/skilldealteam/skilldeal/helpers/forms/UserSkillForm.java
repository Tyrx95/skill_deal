package com.skilldealteam.skilldeal.helpers.forms;

import com.skilldealteam.skilldeal.persistence.model.tables.SkillCategory;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;

import java.util.UUID;

public class UserSkillForm {

    private Integer lessonPrice;
    private String description;
    private UUID skillId;
    private UUID userId;
    private boolean videoLesson;
    private boolean liveMeeting;

    public UserSkillForm(){}

    public Integer getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Integer lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getSkillId() {
        return skillId;
    }

    public void setSkillId(UUID skillId) {
        this.skillId = skillId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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

    public UserSkill createUserSkill(User user, SkillCategory skillCategory){
        UserSkill userSkill = new UserSkill();
        userSkill.setDescription(this.description);
        userSkill.setLessonPrice(this.lessonPrice);
        userSkill.setGivesLiveMeeting(this.liveMeeting);
        userSkill.setGivesVideoLesson(this.videoLesson);
        userSkill.setSkill(skillCategory);
        userSkill.setUser(user);

        return userSkill;
    }
}
