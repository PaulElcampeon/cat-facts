package com.facts.catfacts.utils;

import com.facts.catfacts.models.Fact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomFileReaderTest {

    private final static String PATH = "src/test/test.txt";
    @InjectMocks
    private CustomFileReader underTest;

    @Mock
    private LineToFactMapper lineToFactMapper;


    @Test
    void whenReadFileReturnListIsCalledItShouldReturnListOfTwoFacts() throws IOException {
        //when
        when(lineToFactMapper.apply(anyString())).thenReturn(new Fact("s"));
        List<Fact> twoFacts = underTest.readFileReturnList(PATH);

        //then
        verify(lineToFactMapper, times(2)).apply(anyString());
        verify(lineToFactMapper).apply("When cats climb a tree, they can't go back down it head first. This is because their claws are facing the same way, instead, they have to go back down backward.");
        verify(lineToFactMapper).apply("A group of cats is called a “clowder.”");

        assertEquals(2,twoFacts.size());
    }

    @Test
    void whenFilePathDoesNotExistExceptionShouldBeThrown() throws IOException {
        //when
        String nonExistingFile = "fake.test";

        //then
        assertThrows(IOException.class, () -> {
            underTest.readFileReturnList(nonExistingFile);
        });
    }

}
