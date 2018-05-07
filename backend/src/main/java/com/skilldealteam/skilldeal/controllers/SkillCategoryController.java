package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.persistence.model.tables.SkillCategory;
import com.skilldealteam.skilldeal.services.SkillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class SkillCategoryController extends BaseController {

    @Autowired
    private SkillCategoryService service;

    @RequestMapping(value = "/api/skillCategories", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getSkillCategories() {
        return wrapForPublic(() ->
           this.service.getSkillCategories());
    }

    //temporary for testing purposes
    @RequestMapping(value = "/api/createSkillCategory", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity createCity(@RequestBody SkillCategory skillCategory) {
        return wrapForPublic(() -> this.service.createSkillCategory(skillCategory));
    }

    @RequestMapping(value = "/api/userSkillsByCategory/{skillCategoryId}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getUserSkillsByCategory(@PathVariable final String skillCategoryId) {
        return wrapForPublic(() ->
                this.service.getUserSkills(UUID.fromString(skillCategoryId)));
    }


}
