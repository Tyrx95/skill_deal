package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.helpers.forms.UserSkillForm;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;
import com.skilldealteam.skilldeal.services.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class UserSkillController extends BaseController {

    @Autowired
    private UserSkillService service;


    @RequestMapping(value = "/api/userSkills", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity createUserSkill(@RequestBody UserSkillForm userSkillForm) {
        return wrapForPublic(() -> this.service.createUserSkill(userSkillForm));
    }

    @RequestMapping(value = "/api/userSkills/{skillId}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity deleteUserSkill(@PathVariable String skillId) {
        return wrapForPublic(() -> this.service.deleteUserSkill(UUID.fromString(skillId)));
    }

    @RequestMapping(value = "/api/userSkills/{userId}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getUserSkills(@PathVariable String userId) {
        return wrapForPublic(() -> this.service.getAllUserSkills(UUID.fromString(userId)));
    }

}
