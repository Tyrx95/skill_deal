package com.skilldealteam.skilldeal.controllers;
/*
Copied and Modified
Source com.skilldealteam.skilldeal.controllers.UserSkillController;
*/
import com.skilldealteam.skilldeal.persistence.model.tables.Location;
import com.skilldealteam.skilldeal.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
/*
import org.springframework.web.bind.annotation.PathVariable;
*/
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class LocationController extends BaseController {

    @Autowired
    private LocationService service;


    @RequestMapping(value = "/api/locations", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getLocations() {

        return wrapForPublic(() -> this.service.getAllLocations());
    }

    //temporary for testing purposes
    @RequestMapping(value = "/api/createLocation", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity createLocation(@RequestBody Location location) {
        return wrapForPublic(() -> this.service.createLocation(location));
    }


}