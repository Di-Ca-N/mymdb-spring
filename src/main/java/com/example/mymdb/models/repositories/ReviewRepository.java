package com.example.mymdb.models.repositories;

import com.example.mymdb.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByMovie_Id(Long movieId);
}
