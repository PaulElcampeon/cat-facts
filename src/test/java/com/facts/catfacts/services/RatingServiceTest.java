package com.facts.catfacts.services;

import com.facts.catfacts.models.Rating;
import com.facts.catfacts.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @InjectMocks
    private RatingServiceImpl underTest;
    @Mock
    private RatingRepository repository;

    @Test
    void whenGetRatingIsCalledShouldReturnRatingObject() {
        //when
        Optional<Rating> expected = Optional.of(new Rating());

        when(repository.findById(anyLong())).thenReturn(expected);

        Optional<Rating> result = underTest.getRating(1l);

        //then
        verify(repository).findById(anyLong());
        assertEquals(expected.get(), result.get());
    }

    @Test
    void whenGetAllIsCalledFindAllShouldAlsoBeCalled() {
        //when
        underTest.getAll();

        //then
        verify(repository).findAll();
    }

    @Test
    void whenAddRatingIsCalledSaveShouldAlsoBeCalled() {
        //when
        underTest.addRating(new Rating());

        //then
        verify(repository).save(any(Rating.class));
    }

    @Test
    void whenUpdateRatingIsCalledSaveShouldAlsoBeCalled() {
        //when
        underTest.updateRating(new Rating());

        //then
        verify(repository).save(any(Rating.class));
    }

    @Test
    void whenRemoveRatingIsCalledDeleteByIdShouldAlsoBeCalled() {
        //when
        underTest.removeRating(1l);

        //then
        verify(repository).deleteById(anyLong());
    }

    @Test
    void whenWeCallCountItShouldCallTheRepoCountFunc() {
        //when
        underTest.count();

        //then
        verify(repository).count();
    }
}
