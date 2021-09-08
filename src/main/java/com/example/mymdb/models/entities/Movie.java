package com.example.mymdb.models.entities;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Movie {
    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private String director;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Picture> pictures;

    @Transient
    private Float averageRating;

    public Movie() {
    }

    public Movie(String title, String description, String director, Set<Review> reviews, Set<Picture> pictures) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.reviews = reviews;
        this.pictures = pictures;
    }

    @PostLoad
    private void postLoad() {
        this.averageRating = getAverageScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id) &&
                title.equals(movie.title) &&
                description.equals(movie.description) &&
                director.equals(movie.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, director);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public void addPicture(Picture picture) {
        this.pictures.add(picture);
    }

    public Float getAverageScore(){
        if (!this.reviews.isEmpty()) {
            int totalScore = this.reviews.stream()
                    .map(Review::getScore)
                    .reduce(0, Integer::sum);
            return ((float) totalScore) / this.reviews.size();
        } else {
            return null;
        }
    }
}
