package com.example.mymdb.controllers;

import com.example.mymdb.assemblers.MovieReviewModelAssembler;
import com.example.mymdb.exceptions.MovieNotFoundException;
import com.example.mymdb.models.entities.Movie;
import com.example.mymdb.models.entities.Review;
import com.example.mymdb.models.repositories.MovieRepository;
import com.example.mymdb.models.repositories.ReviewRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MovieReviewModelAssembler reviewModelAssembler;

    public ReviewController(ReviewRepository reviewRepository, MovieRepository movieRepository,
                            MovieReviewModelAssembler reviewModelAssembler) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.reviewModelAssembler = reviewModelAssembler;
    }

    @GetMapping("/movies/{movieId}/reviews")
    public CollectionModel<EntityModel<Review>> all(@PathVariable Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        List<EntityModel<Review>> reviews = reviewRepository.findReviewsByMovie_Id(movieId)
                .stream()
                .map(reviewModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(reviews, linkTo(methodOn(ReviewController.class).all(movie.getId())).withRel("all"));
    }

    @PostMapping("/movies/{moveId}/reviews")
    public EntityModel<Review> newReview(@PathVariable Long moveId, @Valid @RequestBody Review review) {
        Movie movie = movieRepository.findById(moveId).orElseThrow(() -> new MovieNotFoundException(moveId));
        review.setMovie(movie);
        return reviewModelAssembler.toModel(reviewRepository.save(review));
    }

    @DeleteMapping("/reviews/{id}")
    void deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
    }
}
