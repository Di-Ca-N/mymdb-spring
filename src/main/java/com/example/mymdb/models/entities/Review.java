package com.example.mymdb.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;


@Entity
public class Review {
    private @Id @GeneratedValue Long id;
    private String author;
    private String text;

    @Min(value = 0, message = "Invalid value")
    @Max(value = 5, message = "Invalid value")
    private int score;

    @ManyToOne
    @JsonBackReference
    private Movie movie;

    public Review() {
    }

    public Review(String author, String text, Integer score, Movie movie) {
        this.author = author;
        this.text = text;
        this.score = score;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return id.equals(review.id) && author.equals(review.author) && text.equals(review.text) && score == review.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, text, score);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
