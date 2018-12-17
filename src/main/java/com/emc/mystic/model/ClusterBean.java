package com.emc.mystic.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@ApiModel(value = "Cluster", description = "The model to represent a VxRail cluster")
@Entity
@Table(name = "cluster")
public class ClusterBean {

    @ApiModelProperty(notes = "Unique identifier of the cluster.", required = true)
    @Id
    protected Long id;

    @ApiModelProperty(notes = "Cluster name.", required = true, position = 1)
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
