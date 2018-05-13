package com.skilldealteam.skilldeal.controllers;

import com.google.common.cache.LoadingCache;
import com.skilldealteam.skilldeal.exceptions.ServiceException;
import com.skilldealteam.skilldeal.helpers.ErrorMessage;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Callable;

abstract class BaseController{

    @Autowired
    LoadingCache<String, User> userCache;

    @Autowired
    HttpSession httpSession;


    private ResponseEntity catchExceptions(final Callable block) {
        try {
            return (ResponseEntity) block.call();
        } catch (ServiceException se) {
            se.printStackTrace();
            String message = se.getMessage() != null ? se.getMessage() : "Unknown Error";
            return ResponseEntity.badRequest().body("Service exception");
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage() != null ? e.getMessage() : "Unknown Error";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage().setDescription(e.getMessage()));
        }
    }


    ResponseEntity wrapForPublic(final Callable block) {
        return catchExceptions(() -> ResponseEntity.ok(block.call()));
    }

    ResponseEntity wrapForUser(final Callable block) {
        return catchExceptions(() -> {
            User user = this.userCache.get((String) httpSession.getAttribute("uid"));
            if (user != null) {
                return wrapForPublic(block);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("You must be logged in to have access to this Request");
            }
        });
    }

    ResponseEntity wrapForAdmin(final Callable block) {
        return catchExceptions(() -> {
            String uid = (String) httpSession.getAttribute("uid");
            User user = null;
            if(uid != null){
                user = this.userCache.get(uid);
            }
            if (user != null && user.getIsAdmin()) {
                return wrapForPublic(block);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Only Administrators have access to this Request");
            }
        });
    }

}
