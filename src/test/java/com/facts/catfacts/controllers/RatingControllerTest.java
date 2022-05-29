package com.facts.catfacts.controllers;

import com.facts.catfacts.models.Rating;
import com.facts.catfacts.services.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RatingService ratingService;

    @Test
    public void whenIdGivenShouldReturnRatingAssociatedWithId() throws Exception {
        //when
        this.mockMvc.perform(get("/ratings/2")).andReturn();

        //then
        verify(ratingService).getRating(2l);
    }

    @Test
    public void whenRatingIsAddedShouldCallAddRating() throws Exception {
        //when
        Rating rating = new Rating();
        String ratingAsJsonString = objectMapper.writeValueAsString(rating);

        this.mockMvc
                .perform(post("/ratings/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(ratingAsJsonString))
                .andReturn();

        //then
        verify(ratingService).addRating(any(Rating.class));
    }

    @Test
    public void whenRatingIsUpdatedShouldCallUpdateRating() throws Exception {
        //when
        Rating rating = new Rating();
        String ratingAsJsonString = objectMapper.writeValueAsString(rating);

        this.mockMvc
                .perform(put("/ratings/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(ratingAsJsonString))
                .andReturn();

        //then
        verify(ratingService).updateRating(any(Rating.class));
    }

    @Test
    public void whenIdGivenShouldCallRemoveRating() throws Exception {
        //when
        this.mockMvc.perform(delete("/ratings/2")).andReturn();

        //then
        verify(ratingService).removeRating(2l);
    }
}
