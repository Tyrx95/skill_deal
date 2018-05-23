package com.skilldealteam.skilldeal.services;


import com.skilldealteam.skilldeal.helpers.forms.RateForm;
import com.skilldealteam.skilldeal.persistence.model.tables.Location;
import com.skilldealteam.skilldeal.persistence.model.tables.Rating;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends BaseService{

    public boolean rateUser(RateForm rateForm) {
        User ratedUser = (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("id", rateForm.getUserId()))
                .uniqueResult();
        User ratingUser = (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("id", rateForm.getRaterId()))
                .uniqueResult();
        Rating rating = new Rating(rateForm.getRating(),ratedUser, ratingUser);
        getSession().save(rating);
        return true;
    }

}
