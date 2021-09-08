package com.example.mymdb.models;

import com.example.mymdb.models.entities.Movie;
import com.example.mymdb.models.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;


@Configuration
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository) {
        return args -> {
            logger.info("Preloading: " + movieRepository.save(
                    new Movie("Senhor dos Anéis - A Sociedade do Anel",
                            "Um filme da série Senhor dos Anéis",
                            "Peter Jackson",
                            new HashSet<>(),
                            new HashSet<>())
            ));
            logger.info("Preloading: " + movieRepository.save(
                    new Movie(
                            "Senhor dos Anéis - As duas torres",
                            "Segundo filme da série 'Senhor dos Anéis'",
                            "Peter Jackson",
                            new HashSet<>(),
                            new HashSet<>())
            ));
        };
    }
}
