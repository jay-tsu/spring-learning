package com.emc.mystic.model;

import com.emc.mystic.util.db.StringListConverter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "log_bundle")
public class LogBundleBean {

    @Id
    private UUID id;
    private String name;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> types;

    private int size;

    private Timestamp creationtime;

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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCreationtime() {
        return creationtime == null ? null : creationtime.toInstant().toString();
    }

    public void setCreationtime(Timestamp creationtime) {
        this.creationtime = creationtime;
    }

}
