package com.skilldealteam.skilldeal.controllers;

import com.skilldealteam.skilldeal.helpers.SessionUser;
import com.skilldealteam.skilldeal.helpers.forms.LoginForm;
import com.skilldealteam.skilldeal.helpers.forms.RegisterForm;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class UserController extends BaseController {


    @Autowired
    private UserService service;

    @RequestMapping(value = "/api/v1/login", method = RequestMethod.POST, produces="application/json")
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

    @RequestMapping(value = "/api/v1/register",
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

    @RequestMapping(value = "/api/v1/admin/getAllUsers", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getAllUsers() {
        return wrapForAdmin(() -> this.service.getAllUsers());
    }

    @RequestMapping(value = "/api/v1/admin/getUser/{userId}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getUser(@PathVariable final String userId) {
        return wrapForAdmin(() -> this.service.getUser(UUID.fromString(userId)));
    }

    @RequestMapping(value = "/api/v1/admin/editUser", method = RequestMethod.PATCH, produces="application/json")
    public ResponseEntity editUser(@RequestBody User user) {
        return wrapForAdmin(() -> this.service.editUser(user));
    }

    @RequestMapping(value = "/api/v1/admin/deleteUser/{userId}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        return wrapForAdmin(() -> this.service.deleteUser(UUID.fromString(userId)));
    }
}
