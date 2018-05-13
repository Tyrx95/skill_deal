package com.skilldealteam.skilldeal.persistence.model.tables;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilldealteam.skilldeal.persistence.model.BaseModel;
import com.skilldealteam.skilldeal.services.Passwords;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.*;

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

    @ManyToOne()
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToOne()
    @JoinColumn(name = "home_location_id", referencedColumnName = "id")
    private Location homeLocation;

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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserSkill> skills;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Lesson> studentLessons;

    @JsonIgnore
    @OneToMany(mappedBy = "tutor")
    private List<Lesson> tutorLessons;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<LessonRequest> studentLessonRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "tutor")
    private List<LessonRequest> tutorLessonRequests;


    @OneToMany(mappedBy = "ratedUser", fetch = FetchType.EAGER)
    private List<Rating> ratedUserRating;

    @JsonIgnore
    @OneToMany(mappedBy = "ratingUser")
    private List<Rating> ratingUserRatings;

    @Transient
    private Double rating;

    @Transient
    private Integer numberOfRatings;

    public User() { }

    public User(String firstName, String lastName, String email, String password, Location location, Location homeLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.location = location;
        this.homeLocation = homeLocation;
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

    public Location getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public Double getRating() {
        OptionalDouble average = this.ratedUserRating.stream().mapToInt(Rating::getRating).average();
        return average.isPresent() ? average.getAsDouble() : 0D;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<LessonRequest> getStudentLessonRequests() {
        return studentLessonRequests;
    }

    public void setStudentLessonRequests(List<LessonRequest> studentLessonRequests) {
        this.studentLessonRequests = studentLessonRequests;
    }

    public List<LessonRequest> getTutorLessonRequests() {
        return tutorLessonRequests;
    }

    public void setTutorLessonRequests(List<LessonRequest> tutorLessonRequests) {
        this.tutorLessonRequests = tutorLessonRequests;
    }

    public List<Lesson> getStudentLessons() {
        return studentLessons;
    }

    public void setStudentLessons(List<Lesson> studentLessons) {
        this.studentLessons = studentLessons;
    }

    public List<Lesson> getTutorLessons() {
        return tutorLessons;
    }

    public void setTutorLessons(List<Lesson> tutorLessons) {
        this.tutorLessons = tutorLessons;
    }

    public List<Rating> getRatedUserRating() {
        return ratedUserRating;
    }

    public void setRatedUserRating(List<Rating> ratedUserRating) {
        this.ratedUserRating = ratedUserRating;
    }

    public List<Rating> getRatingUserRatings() {
        return ratingUserRatings;
    }

    public void setRatingUserRatings(List<Rating> ratingUserRatings) {
        this.ratingUserRatings = ratingUserRatings;
    }
}
