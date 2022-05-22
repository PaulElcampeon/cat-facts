package com.facts.catfacts.utils;

import com.facts.catfacts.models.Fact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LineToFactMapperTest {

    @InjectMocks
    private LineToFactMapper underTest;

    @Test
    void whenApplyMethodCalledItShouldReturnFactEquivalent() {
        //when
        Fact result = underTest.apply("test");

        //then
        assertEquals("test", result.getFact());
    }
}
