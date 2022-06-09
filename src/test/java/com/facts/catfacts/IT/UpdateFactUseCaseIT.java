package com.facts.catfacts.IT;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
import com.facts.catfacts.services.FactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateFactUseCaseIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FactService factService;

    @Autowired
    private FactRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void updateFactWorksThroughAllLayers() throws Exception {
        //when
        Fact requestBodyFact = new Fact("blank");
        //Fact will be given id when inserted to database
        factService.addFact(requestBodyFact);

        String updateFact = "How many chickens";

        requestBodyFact.setFact(updateFact);

        MvcResult mvcResult =
                mockMvc.perform(put("/facts/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestBodyFact)))
                        .andExpect(status().isOk())
                        .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Fact persistedFact = objectMapper.readValue(result, Fact.class);

        Optional<Fact> fact = factService.getFact(persistedFact.getId());

        assertEquals(updateFact, fact.get().getFact());
    }

    @After
    public void cleanUp() {
        repository.deleteAll();
    }
}
