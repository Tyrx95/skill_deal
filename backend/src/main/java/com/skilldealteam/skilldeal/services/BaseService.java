package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.persistence.model.tables.Notification;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime now = LocalDateTime.now();
        String month = String.valueOf(now.getMonth());
        month = month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase();
        String timestamp = String.valueOf(now.getDayOfMonth()) +" "
                + month +" "+ + now.getYear() + " at " + now.getHour() + ":" + now.getMinute();

        Notification notification = new Notification();
        notification.setReceiver(reciever);
        notification.setContent(content);
        notification.setFAIcon(icon);
        notification.setTimestamp(timestamp);
        notification.setOpened(false);
        getSession().save(notification);
    }
}