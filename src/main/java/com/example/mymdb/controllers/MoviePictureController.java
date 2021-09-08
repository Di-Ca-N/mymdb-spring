package com.example.mymdb.controllers;

import com.example.mymdb.exceptions.MovieNotFoundException;
import com.example.mymdb.models.entities.Movie;
import com.example.mymdb.models.entities.Picture;
import com.example.mymdb.models.repositories.MovieRepository;
import com.example.mymdb.models.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviePictureController {
    private final PictureRepository pictureRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MoviePictureController(PictureRepository pictureRepository, MovieRepository movieRepository) {
        this.pictureRepository = pictureRepository;
        this.movieRepository = movieRepository;
    }

    @PostMapping("/movie/{movieId}/pictures")
    public Picture savePicture(@PathVariable Long movieId, @RequestBody Picture picture) {
        Picture savedPicture = pictureRepository.save(picture);
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        movie.addPicture(savedPicture);
        movieRepository.save(movie);
        return savedPicture;
    }
}
