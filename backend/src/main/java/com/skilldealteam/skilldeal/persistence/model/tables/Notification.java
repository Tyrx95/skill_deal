package com.skilldealteam.skilldeal.persistence.model.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilldealteam.skilldeal.persistence.model.BaseModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification extends BaseModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", referencedColumnName = "id")
    private User receiver;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "content")
    private String content;

    @Column(name = "fa_icon")
    private String FAIcon;

    @Column(name = "opened")
    private Boolean opened;

    public Notification(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFAIcon() {
        return FAIcon;
    }

    public void setFAIcon(String FAIcon) {
        this.FAIcon = FAIcon;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }
}
