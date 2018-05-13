package com.skilldealteam.skilldeal.persistence.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "rating")
    private Integer rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rated_user_id", referencedColumnName = "id")
    private User ratedUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_user_id", referencedColumnName = "id")
    private User ratingUser;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    public User getRatingUser() {
        return ratingUser;
    }

    public void setRatingUser(User ratingUser) {
        this.ratingUser = ratingUser;
    }
}
