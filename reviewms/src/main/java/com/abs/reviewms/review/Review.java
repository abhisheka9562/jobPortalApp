package com.abs.reviewms.review;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Review
{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
