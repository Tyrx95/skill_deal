package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.helpers.NotificationMessage;
import com.skilldealteam.skilldeal.helpers.forms.LessonRequestForm;
import com.skilldealteam.skilldeal.persistence.model.tables.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            createNotification(lessonRequest.getTutor(),
                    NotificationMessage.sentLessonRequestMessage(lessonRequest.getStudent().getFirstName(), lessonRequest.getStudent().getLastName()),
                    NotificationMessage.SENT_LESSON_REQUEST_ICON);
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

    public boolean confirmLessonRequest(UUID lessonRequestId) throws Exception {

        LessonRequest lessonRequest = (LessonRequest) getSession().createCriteria(LessonRequest.class)
                .add(Restrictions.eq("id", lessonRequestId))
                .uniqueResult();
        //check if amount is enough
        if (lessonRequest.getStudent().getSkillPoints() > lessonRequest.getSkill().getLessonPrice()) {
            //createLesson
            boolean success = createLesson(lessonRequestId);
            if (success) {
                //deleteLessonRequest
                this.deleteLessonRequest(lessonRequestId, false);
                //makePayment
                makePayment(lessonRequest.getTutor(), lessonRequest.getStudent(), lessonRequest.getSkill().getLessonPrice());
                //createNotification
                createNotification(lessonRequest.getStudent(),
                        NotificationMessage.confirmedLessonRequestMessage(lessonRequest.getTutor().getFirstName(), lessonRequest.getTutor().getLastName()),
                        NotificationMessage.CONFIRMED_LESSON_REQUEST_ICON);
                return true;
            }
        }
        return false;
    }

    public boolean deleteLessonRequest(UUID lessonRequestId, boolean createNotification) {
        LessonRequest lessonRequest = (LessonRequest) getSession()
                .createCriteria(LessonRequest.class)
                .add(Restrictions.eq("id", lessonRequestId))
                .uniqueResult();
        getSession().delete(lessonRequest);
        if (createNotification) {
            createNotification(lessonRequest.getStudent(),
                    NotificationMessage.canceledLessonRequestMessage(lessonRequest.getTutor().getFirstName(), lessonRequest.getTutor().getLastName()),
                    NotificationMessage.CANCELED_LESSON_REQUEST_ICON);
        }
        return true;
    }


    private boolean createLesson(UUID lessonRequestId) {
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

    private boolean makePayment(User tutor, User student, int price) throws Exception {
        tutor.setSkillPoints(tutor.getSkillPoints() + price);
        getSession().update(tutor);
        student.setSkillPoints(student.getSkillPoints() - price);
        getSession().update(student);
        return true;
    }

}
