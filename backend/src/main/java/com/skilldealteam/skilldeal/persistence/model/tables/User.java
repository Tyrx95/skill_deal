package com.skilldealteam.skilldeal.persistence.model.tables;


import com.skilldealteam.skilldeal.persistence.model.BaseModel;
import com.skilldealteam.skilldeal.services.Passwords;

import javax.persistence.*;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * The User model
 */
@Entity
@Table(name = "\"user\"")
public class User extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "biography")
    private String biography;

    @Column(name = "email")
    private String email;

    @Column(name = "hash")
    private String hash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "image_uri")
    private String imageUri;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "skill_points")
    private Integer skillPoints = 0;

    @OneToMany(mappedBy = "userId")
    private Set<UserSkill> skills;

    @OneToMany(mappedBy = "userId")
    private Set<Review> receivedReviews;

    @OneToMany(mappedBy = "userId")
    private Set<Review> givenReviews;

    @OneToMany(mappedBy = "userId")
    private Set<Lesson> lessons;


    public User() { }

    public User(String firstName, String lastName, String email, String password, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.location = location;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) {
        this.salt = base64Encode(Passwords.getNextSalt());
        this.hash = base64Encode(Passwords.hash(password.toCharArray(), base64Decode(this.salt)));
    }

    private String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] base64Decode(String string) {
        return Base64.getDecoder().decode(string);
    }

    public Boolean getIsAdmin() { return isAdmin; }

    public void setIsAdmin(Boolean admin) { isAdmin = admin; }

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Integer getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(Integer skillPoints) {
        this.skillPoints = skillPoints;
    }

    public Set<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<UserSkill> skills) {
        this.skills = skills;
    }

    public Set<Review> getReceivedReviews() {
        return receivedReviews;
    }

    public void setReceivedReviews(Set<Review> receivedReviews) {
        this.receivedReviews = receivedReviews;
    }

    public Set<Review> getGivenReviews() {
        return givenReviews;
    }

    public void setGivenReviews(Set<Review> givenReviews) {
        this.givenReviews = givenReviews;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
