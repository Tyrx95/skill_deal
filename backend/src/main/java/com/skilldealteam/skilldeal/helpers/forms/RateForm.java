package com.skilldealteam.skilldeal.helpers.forms;

import java.util.UUID;

public class RateForm {

    private Integer rating;
    private UUID userId;
    private UUID raterId;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRaterId() {
        return raterId;
    }

    public void setRaterId(UUID raterId) {
        this.raterId = raterId;
    }
}
