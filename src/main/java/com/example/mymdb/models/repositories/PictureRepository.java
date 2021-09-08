package com.example.mymdb.models.repositories;

import com.example.mymdb.models.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
