package com.its.sims.model;

/**
 * Created by csz on 2017/9/16.
 */
public class Role {

    private Long id;

    private String name;

    private String resourceIds;


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

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                '}';
    }
}
