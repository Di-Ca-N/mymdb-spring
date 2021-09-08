package com.example.mymdb.assemblers;

import com.example.mymdb.controllers.MovieController;
import com.example.mymdb.controllers.ReviewController;
import com.example.mymdb.models.entities.Review;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MovieReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {
    @Override
    public EntityModel<Review> toModel(Review review) {
        return EntityModel.of(review,
                linkTo(methodOn(ReviewController.class).all(review.getMovie().getId())).withRel("all"),
                linkTo(methodOn(MovieController.class).one(review.getMovie().getId())).withRel("movie")
        );
    }
}
