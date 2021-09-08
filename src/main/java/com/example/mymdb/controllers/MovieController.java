package com.example.mymdb.controllers;

import com.example.mymdb.assemblers.MovieModelAssembler;
import com.example.mymdb.models.entities.Movie;
import com.example.mymdb.exceptions.MovieNotFoundException;
import com.example.mymdb.models.repositories.MovieRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class MovieController {
    private final MovieRepository repository;
    private final MovieModelAssembler assembler;

    public MovieController(MovieRepository repository, MovieModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies")
    public CollectionModel<EntityModel<Movie>> all() {
        List<EntityModel<Movie>> movies = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(movies, linkTo(methodOn(MovieController.class).all()).withSelfRel());
    }

    @PostMapping("/movies")
    public EntityModel<Movie> newMovie(@RequestBody Movie movie) {
        movie = repository.save(movie);
        return assembler.toModel(movie);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies/{id}")
    public EntityModel<Movie> one(@PathVariable Long id) {
        Movie movie = repository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return assembler.toModel(movie);
    }

    @PutMapping("/movies/{id}")
    public EntityModel<Movie> replaceMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        return repository.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDescription(newMovie.getDescription());
                    movie.setDirector(newMovie.getDirector());
                    movie.setPictures(newMovie.getPictures());
                    return assembler.toModel(repository.save(movie));
                }).orElseGet(() -> {
                    newMovie.setId(id);
                    return assembler.toModel(repository.save(newMovie));
                });
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
