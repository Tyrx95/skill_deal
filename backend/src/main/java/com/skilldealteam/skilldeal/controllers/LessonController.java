package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class LessonController extends BaseController {

    @Autowired
    LessonService service;

    @RequestMapping(value = "/api/lessons/tutor/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLessonsTutor(@PathVariable final String userId) {
        return wrapForPublic(() ->
                this.service.getTutorLessons(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/lessons/student/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLessonsStudent(@PathVariable final String userId) {
        return wrapForPublic(() ->
                this.service.getStudentLessons(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/lessons/{lessonId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteLesson(@PathVariable final String lessonId) {
        return wrapForPublic(() ->
                this.service.deleteLesson(UUID.fromString(lessonId)));
    }


}

