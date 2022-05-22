package com.facts.catfacts.services;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
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
public class FactServiceTest {

    @InjectMocks
    private FactServiceImpl underTest;
    @Mock
    private FactRepository repository;

    @Test
    void whenGetFactIsCalledShouldReturnFactObject() {
        //when
        Optional<Fact> expected = Optional.of(new Fact("s"));
        when(repository.findById(anyLong())).thenReturn(expected);
        Optional<Fact> result = underTest.getFact(1l);

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
    void whenAddFactIsCalledSaveShouldAlsoBeCalled() {
        //when
        underTest.addFact(new Fact("s"));

        //then
        verify(repository).save(any(Fact.class));
    }

    @Test
    void whenRemoveFactIsCalledDeleteByIdShouldAlsoBeCalled() {
        //when
        underTest.removeFact(1l);

        //then
        verify(repository).deleteById(anyLong());
    }
}