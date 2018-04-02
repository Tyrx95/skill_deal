package com.skilldealteam.skilldeal.helpers;

import com.skilldealteam.skilldeal.persistence.model.tables.User;

public class SessionUser {

    private Boolean isLoggedIn = false;
    private User object;

    public SessionUser() { }

    public SessionUser(User object) {
        this.object = object;
        this.isLoggedIn = true;
    }

    public Boolean getIsLoggedIn() { return this.isLoggedIn; }

    public User getObject() { return this.object; }
}
