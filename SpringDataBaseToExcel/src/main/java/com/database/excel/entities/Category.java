package com.database.excel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private int id;

    @Column(length = 60, nullable = false)
    private String title;

    @Column(length = 500)
    private String about;

    private String coverImage;

    public Category() {
    }

    public Category(int id, String title, String about, String coverImage) {
        this.id = id;
        this.title = title;
        this.about = about;
        this.coverImage = coverImage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return about;
    }

    public void setDescription(String description) {
        this.about = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
