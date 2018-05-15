package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.helpers.NotificationMessage;
import com.skilldealteam.skilldeal.persistence.model.tables.Lesson;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonService extends BaseService {

    public boolean deleteLesson(UUID lessonId) {
        Lesson lesson = (Lesson) getSession().createCriteria(Lesson.class)
                .add(Restrictions.eq("id", lessonId))
                .uniqueResult();
        getSession().delete(lesson);
        createNotification(lesson.getStudent(),
                NotificationMessage.canceledLessonMessage(lesson.getTutor().getFirstName(), lesson.getTutor().getLastName()),
                NotificationMessage.CANCELED_LESSON_ICON);
        return true;
    }

    public List<Lesson> getTutorLessons(UUID tutorId) {
        return (List<Lesson>) getSession().createCriteria(Lesson.class).add(Restrictions.eq("tutor.id", tutorId)).list();

    }

    public List<Lesson> getStudentLessons(UUID studentId) {
        return (List<Lesson>) getSession().createCriteria(Lesson.class).add(Restrictions.eq("student.id", studentId)).list();

    }


}
