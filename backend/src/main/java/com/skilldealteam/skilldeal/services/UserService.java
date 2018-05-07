package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.exceptions.ServiceException;
import com.skilldealteam.skilldeal.helpers.forms.LoginForm;
import com.skilldealteam.skilldeal.helpers.forms.RegisterForm;
import com.skilldealteam.skilldeal.persistence.model.tables.Location;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService extends BaseService {

    public User authenticate(final LoginForm loginForm) throws ServiceException, NullPointerException {
        try {
            if (checkCredentials(
                    loginForm.getEmail(),
                    base64Encode(
                            Passwords.hash(
                                    loginForm.getPassword().toCharArray(),
                                    getSaltForUser(loginForm.getEmail())
                            )
                    )
            )) {
                return get(loginForm.getEmail());
            } else {
                throw new ServiceException("Login Error", "Invalid Password");
            }
        } catch (NullPointerException e) {
            throw new ServiceException("Login Error", "Invalid Email");
        }
    }

    public User register(final RegisterForm registerForm) {
        try {
            Location location = (Location) getSession().createCriteria(Location.class)
                    .add(Restrictions.eq("id", registerForm.getLocationId()))
                    .uniqueResult();
            Location homeLocation = (Location) getSession().createCriteria(Location.class)
                    .add(Restrictions.eq("id", registerForm.getHomeLocationId()))
                    .uniqueResult();
            User newUser = registerForm.createAccount(location, homeLocation);
            getSession().save(newUser);
            return newUser;
        } catch (Exception e) {
            throw e;
        }
    }

    private byte[] getSaltForUser(final String email) {
        String userSalt = (String) getSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .setProjection(Projections.property("salt"))
                .uniqueResult();

        if (userSalt != null) {
            return base64Decode(userSalt);
        } else {
            return null;
        }
    }

    private Boolean checkCredentials(final String email, final String hash) {
        User user = (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("hash", hash))
                .uniqueResult();

        return user != null;
    }

    public User get(final String email) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return (List<User>) getSession().createCriteria(User.class).list();
    }

    public User getUser(final UUID userId) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("id", userId))
                .uniqueResult();
    }

    public Boolean editUser(User user) {
        if (user.getId() != null) {
            User dbUser = this.getUser(user.getId());
            dbUser.setIsAdmin(user.getIsAdmin());

            getSession().update(dbUser);
            return true;
        }
        return false;
    }

    public Boolean deleteUser(final UUID id) throws Exception {
        User user = (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

        getSession().delete(user);
        return true;
    }
}

