package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.helpers.MarkReadPOJO;
import com.skilldealteam.skilldeal.persistence.model.tables.Notification;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class NotificationService extends BaseService {


    public MarkReadPOJO markRead(UUID userId){
        final String hqlUpdate = "update Notification n set n.opened = true where n.receiver.id = :userId";
        int updatedEntities = getSession().createQuery(hqlUpdate).
                setParameter("userId",userId).executeUpdate();

        return new MarkReadPOJO().setUpdatedEntities(updatedEntities);
    }

    public List<Notification> getNotificationsById(UUID userId){

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Notification> criteriaQuery = builder.createQuery(Notification.class);
        Root<Notification> root = criteriaQuery.from(Notification.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("receiver").get("id"),userId));
        Query<Notification> query = getSession().createQuery(criteriaQuery);
        List<Notification> notifications = query.getResultList();
        Collections.reverse(notifications);
        return notifications;
    }

}
