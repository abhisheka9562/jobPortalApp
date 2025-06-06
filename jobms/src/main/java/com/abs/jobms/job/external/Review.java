package com.abs.jobms.job.external;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


public class Review
{
    private long id;
    private String title;
    private String description;
    private Double rating;

    public Review() {
    }

    public Review(long id, String title, String description, Double rating,Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
