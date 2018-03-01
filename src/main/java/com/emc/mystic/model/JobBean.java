package com.emc.mystic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "job")
public class JobBean {
    @Id
    private UUID id;
    private String description;
    private String owner;
    private String state;
    private String error;
    private int progress;
    private String target;
    private Timestamp starttime;
    private Timestamp endtime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStarttime() {
        return starttime == null ? null : starttime.toInstant().toString();
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime == null ? null : endtime.toInstant().toString();
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }
}