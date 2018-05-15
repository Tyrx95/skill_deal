package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class NotificationController extends BaseController{

    @Autowired
    private NotificationService service;


    @RequestMapping(value = "/api/notifications/{userId}/clear", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity search(@PathVariable final String userId) {
        return wrapForPublic(() ->
                this.service.markRead(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/notifications/{userId}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getNotificationsById(@PathVariable final String userId) {
        return wrapForPublic(() ->
                this.service.getNotificationsById(UUID.fromString(userId)));
    }

}
