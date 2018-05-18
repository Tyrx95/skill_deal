package com.skilldealteam.skilldeal.helpers.forms;

import com.skilldealteam.skilldeal.persistence.model.tables.LessonRequest;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;

import java.time.LocalDateTime;
import java.util.UUID;

public class LessonRequestForm {

    private UUID studentId;
    private UUID tutorId;
    private LocalDateTime timestamp;
    private UUID skillId;
    private String description;

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getSkillId() {
        return skillId;
    }

    public void setSkillId(UUID skillId) {
        this.skillId = skillId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LessonRequest createLessonRequest(User tutor, User student, UserSkill userSkill) {
        LessonRequest lessonRequest = new LessonRequest();
        lessonRequest.setTutor(tutor);
        lessonRequest.setStudent(student);
        lessonRequest.setTimestamp(this.timestamp);
        lessonRequest.setDescription(this.description);
        lessonRequest.setSkill(userSkill);
        return lessonRequest;
    }
}
