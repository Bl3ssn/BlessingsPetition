package com.example.blessingspetitions.entity;

public class Petition {
    private Long id;
    private String title;
    private String description;
    private String creatorName;

    public Petition(Long id, String title, String description, String creatorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorName = creatorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
