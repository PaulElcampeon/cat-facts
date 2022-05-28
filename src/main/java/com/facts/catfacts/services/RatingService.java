package com.facts.catfacts.services;

import com.facts.catfacts.models.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    Optional<Rating> getRating(Long id);

    List<Rating> getAll();

    Rating addRating(Rating rating);

    Rating updateRating(Rating rating);

    void removeRating(Long id);

    long count();

}
