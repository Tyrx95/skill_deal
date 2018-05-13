package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.helpers.forms.LessonRequestForm;
import com.skilldealteam.skilldeal.persistence.model.tables.Lesson;
import com.skilldealteam.skilldeal.persistence.model.tables.LessonRequest;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonRequestService extends BaseService {

    public boolean createLessonRequest(final LessonRequestForm lessonRequestForm) {
        try {
            User tutor = (User) getSession().createCriteria(User.class)
                    .add(Restrictions.eq("id", lessonRequestForm.getTutorId()))
                    .uniqueResult();
            User student = (User) getSession().createCriteria(User.class)
                    .add(Restrictions.eq("id", lessonRequestForm.getStudentId()))
                    .uniqueResult();
            UserSkill userSkill = (UserSkill) getSession().createCriteria(UserSkill.class)
                    .add(Restrictions.eq("id", lessonRequestForm.getSkillId()))
                    .uniqueResult();
            LessonRequest lessonRequest = lessonRequestForm.createLessonRequest(tutor, student, userSkill);
            getSession().save(lessonRequest);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }


    public List<LessonRequest> getLessonRequestsTutor(UUID tutorId) {
        Criteria criteria = getSession()
                .createCriteria(LessonRequest.class);
        criteria.add(Restrictions.eq("tutor.id", tutorId));
        List<LessonRequest> list = criteria.list();
        return criteria.list();
    }

    public List<LessonRequest> getLessonRequestsStudent(UUID studentId) {
        Criteria criteria = getSession()
                .createCriteria(LessonRequest.class);
        criteria.add(Restrictions.eq("student.id", studentId));
        return criteria.list();

    }

    public boolean confirmLessonRequest(UUID lessonRequestId) {
        //createLesson
        boolean success = createLesson(lessonRequestId);
        //deleteLessonRequest
        if (success) {
            this.deleteLessonRequest(lessonRequestId);
            //createNotification
            //makePayment
            return true;
        }
        return false;
    }

    public boolean deleteLessonRequest(UUID lessonRequestId) {
        LessonRequest lessonRequest = (LessonRequest) getSession()
                .createCriteria(LessonRequest.class)
                .add(Restrictions.eq("id", lessonRequestId))
                .uniqueResult();
        getSession().delete(lessonRequest);
        return true;
    }


    public boolean createLesson(UUID lessonRequestId) {
        LessonRequest lessonRequest = (LessonRequest) getSession().createCriteria(LessonRequest.class)
                .add(Restrictions.eq("id", lessonRequestId))
                .uniqueResult();
        Lesson lesson = new Lesson();
        lesson.setSkill(lessonRequest.getSkill());
        lesson.setStudent(lessonRequest.getStudent());
        lesson.setTutor(lessonRequest.getTutor());
        lesson.setTimestamp(lessonRequest.getTimestamp());
        getSession().save(lesson);
        return true;
    }
}
