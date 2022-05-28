package com.facts.catfacts.services;

import com.facts.catfacts.models.Rating;
import com.facts.catfacts.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Optional<Rating> getRating(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void removeRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public long count() {
        return ratingRepository.count();
    }
}
