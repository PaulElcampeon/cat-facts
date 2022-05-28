package com.facts.catfacts.controllers;

import com.facts.catfacts.models.Rating;
import com.facts.catfacts.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Rating getRatingById(@PathVariable long id) {
        return ratingService.getRating(id).orElse(new Rating());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Rating addRating(Rating rating) {
        return ratingService.addRating(rating);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Rating updateRating(Rating rating) {
        return ratingService.updateRating(rating);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRatingById(@PathVariable long id) {
        ratingService.removeRating(id);
    }
}
