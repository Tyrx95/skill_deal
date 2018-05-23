package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.helpers.SessionUser;
import com.skilldealteam.skilldeal.helpers.forms.LoginForm;
import com.skilldealteam.skilldeal.helpers.forms.RegisterForm;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class UserController extends BaseController {


    @Autowired
    private UserService service;

    private static final String IMAGE_DIRECTORY = "./resources/static/images/";

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity login(@RequestBody LoginForm loginForm) {
        return wrapForPublic(() -> {
            User user = this.service.authenticate(loginForm);

            UUID id = UUID.randomUUID();
            httpSession.removeAttribute("uid");
            httpSession.setAttribute("uid",id.toString());
            this.userCache.put(id.toString(), user);
            return user;
        });
    }

    @RequestMapping(value = "/api/v1/logout", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity logout() {
        return wrapForPublic(() -> {
            this.userCache.invalidate(httpSession.getAttribute("uid"));
            httpSession.removeAttribute("uid");
            return "{}";
        });
    }

    @RequestMapping(value = "/api/user/register",
            method = RequestMethod.POST,
            produces="application/json")
    public ResponseEntity register(@RequestBody RegisterForm registerForm) {
        return wrapForPublic(() -> {
            User user = this.service.register(registerForm);
            UUID id = UUID.randomUUID();
            httpSession.removeAttribute("uid");
            httpSession.setAttribute("uid",id.toString());
            this.userCache.put(id.toString(), user);
            return user;
        });
    }

    @RequestMapping(value = "/api/v1/getCurrentUser", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getCurrentUser() {
        return wrapForPublic(() -> {
            String sessionUid = (String) httpSession.getAttribute("uid");
            User user = null;
            if(sessionUid != null) {
                user = this.userCache.get(sessionUid);
            }
            if (user != null) {
                return new SessionUser(user);
            } else {
                return new SessionUser();
            }
        });
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getAllUsers() {
        return wrapForPublic(() -> this.service.getAllUsers());
    }

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getUser(@PathVariable final String userId) {
        return wrapForPublic(() -> this.service.getUser(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.PATCH, produces="application/json")
    public ResponseEntity editUser(@RequestBody User user) {
        return wrapForPublic(() -> this.service.editUser(user));
    }

    @RequestMapping(value = "/api/v1/admin/deleteUser/{userId}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        return wrapForPublic(() -> this.service.deleteUser(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/user/{userId}/picture", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity changePicture(@RequestParam("file") MultipartFile picture,
                                        @PathVariable String userId){
        return wrapForUser(() -> {
            if (picture != null) {
                String extension = getExtension(picture.getOriginalFilename());
                Long timestamp = System.currentTimeMillis();
                String pathString= IMAGE_DIRECTORY + timestamp + "."+ extension;
                byte[] bytes = picture.getBytes();
                Files.write(Paths.get(pathString),bytes);
                String newPath = "/images/" + timestamp + "." +  extension;
                service.updatePictureUrl(UUID.fromString(userId), newPath);
                return "{ \"path\": \"" + newPath+"\"}";
            } else {
                return "{ \"message\": \"" + "failed" + "\"}";
            }});
    }




    private String getExtension(String filename){
        String[] fileNameSplit = filename.split("\\.");
        String fileExtension = fileNameSplit[fileNameSplit.length-1];
        return fileExtension;
    }

}
