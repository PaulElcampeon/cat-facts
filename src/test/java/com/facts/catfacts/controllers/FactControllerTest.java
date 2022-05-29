package com.facts.catfacts.controllers;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.services.FactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(FactController.class)
public class FactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FactService factService;

    @Test
    public void whenRandomEndPointIsQueriedShouldReturnRandomFact() throws Exception {
        //when
        Fact expected = new Fact("random");

        when(factService.getRandomFact()).thenReturn(expected);

        MvcResult mvcResult = this.mockMvc.perform(get("/facts/random")).andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        //then
        assertTrue(result.contains("\"fact\":\"random\""));
        verify(factService).getRandomFact();
    }

    @Test
    public void whenIdGivenShouldReturnFactAssociatedWithId() throws Exception {
        //when
        this.mockMvc.perform(get("/facts/2")).andReturn();

        //then
        verify(factService).getFact(2l);
    }

    @Test
    public void whenFactIsAddedShouldCallAddFact() throws Exception {
        //when
        Fact fact = new Fact("blank");
        String factAsJsonString = objectMapper.writeValueAsString(fact);

        this.mockMvc.perform(post("/facts/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(factAsJsonString))
                .andReturn();

        //then
        verify(factService).addFact(any(Fact.class));
    }

    @Test
    public void whenFactIsUpdatedShouldCallUpdateFact() throws Exception {
        //when
        Fact fact = new Fact("blank");
        String factAsJsonString = objectMapper.writeValueAsString(fact);

        this.mockMvc.perform(put("/facts/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(factAsJsonString))
                .andReturn();

        //then
        verify(factService).updateFact(any(Fact.class));
    }

    @Test
    public void whenIdGivenShouldCallRemoveFact() throws Exception {
        //when
        this.mockMvc.perform(delete("/facts/2")).andReturn();

        //then
        verify(factService).removeFact(2l);
    }
}
