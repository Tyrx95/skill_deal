package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.helpers.forms.RateForm;
import com.skilldealteam.skilldeal.services.NotificationService;
import com.skilldealteam.skilldeal.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class RatingController extends BaseController {

    @Autowired
    private RatingService service;

    @RequestMapping(value = "/api/rating", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity rateUser(@RequestBody final RateForm rateForm) {
        return wrapForPublic(() ->
                this.service.rateUser(rateForm));
    }

}
