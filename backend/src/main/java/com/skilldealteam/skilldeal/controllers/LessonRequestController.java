package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.helpers.forms.LessonRequestForm;
import com.skilldealteam.skilldeal.services.LessonRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class LessonRequestController extends BaseController {

    @Autowired
    LessonRequestService service;

    @RequestMapping(value = "/api/lessonRequests/tutor/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLessonRequestsTutor(@PathVariable final String userId) {
        return wrapForPublic(() ->
                this.service.getLessonRequestsTutor(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/lessonRequests/student/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLessonRequestsStudent(@PathVariable final String userId) {
        return wrapForPublic(() ->
                this.service.getLessonRequestsStudent(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/lessonRequests/tutor/{userId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createLessonRequest(@RequestBody LessonRequestForm lessonRequestForm, @RequestHeader HttpHeaders headers, @PathVariable String userId) {
        lessonRequestForm.setTutorId(UUID.fromString(userId));
        lessonRequestForm.setStudentId(UUID.fromString(headers.get("user").get(0)));
        return wrapForPublic(() ->
                this.service.createLessonRequest(lessonRequestForm));
    }

    @RequestMapping(value = "/api/lessonRequests/{lessonRequestId}/confirm", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createLessonRequest(@PathVariable String lessonRequestId) {
        return wrapForPublic(() ->
                this.service.confirmLessonRequest(UUID.fromString(lessonRequestId)));
    }


    @RequestMapping(value = "/api/lessonRequests/{lessonRequestId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteLessonRequest(@PathVariable final String lessonRequestId) {
        return wrapForPublic(() ->
                this.service.deleteLessonRequest(UUID.fromString(lessonRequestId),true));
    }


}
