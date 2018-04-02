package com.skilldealteam.skilldeal.persistence.model.tables;

import com.skilldealteam.skilldeal.persistence.model.BaseModel;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "location")
public class Location extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "google_lat")
    double googleLat;

    @Column(name = "google_lng")
    double googleLng;

    public Location(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGoogleLat() {
        return googleLat;
    }

    public void setGoogleLat(double googleLat) {
        this.googleLat = googleLat;
    }

    public double getGoogleLng() {
        return googleLng;
    }

    public void setGoogleLng(double googleLng) {
        this.googleLng = googleLng;
    }
}
