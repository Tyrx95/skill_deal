package com.skilldealteam.skilldeal.helpers.forms;

import com.skilldealteam.skilldeal.persistence.model.tables.Location;
import com.skilldealteam.skilldeal.persistence.model.tables.User;

import java.util.Date;
import java.util.UUID;

public class RegisterForm {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String biography;
    private Date dateOfBirth;
    private UUID locationId;
    private String imageUri;

    public RegisterForm() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public User createAccount(Location location) {
        User newUser = new User(this.firstName, this.lastName, this.email, this.password, location);
        newUser.setBiography(biography);
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setEmail(email);
        newUser.setImageUri(imageUri);
        return newUser;
    }
}
