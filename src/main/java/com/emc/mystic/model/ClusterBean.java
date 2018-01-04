package com.emc.mystic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "cluster")
public class ClusterBean {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
