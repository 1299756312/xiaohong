package com.tencent.wxcloudrun.entity;

import java.util.List;

public class Root {
    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;

    private Usage usage;

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }






    public String toString() {
        return "Root(id=" + getId() + ", object=" + getObject() + ", created=" + getCreated() + ", model=" + getModel() + ", choices=" + getChoices() + ", usage=" + getUsage() + ")";
    }

    public String getId() {
        return this.id;
    }

    public String getObject() {
        return this.object;
    }

    public int getCreated() {
        return this.created;
    }

    public String getModel() {
        return this.model;
    }

    public List<Choices> getChoices() {
        return this.choices;
    }

    public Usage getUsage() {
        return this.usage;
    }
}
