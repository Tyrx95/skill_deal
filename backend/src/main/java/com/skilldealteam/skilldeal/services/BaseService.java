package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.persistence.model.tables.Notification;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Base64;

@Transactional
abstract class BaseService {

    @Autowired
    private SessionFactory sessionFactory;

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    String base64Encode(final byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    byte[] base64Decode(final String string) {
        return Base64.getDecoder().decode(string);
    }


    void createNotification(User reciever, String content, String icon) {
        Notification notification = new Notification();
        notification.setReceiver(reciever);
        notification.setContent(content);
        notification.setFAIcon(icon);
        notification.setTimestamp(LocalDateTime.now());
        notification.setOpened(false);
        getSession().save(notification);
    }
}