package com.facts.catfacts.integrationtests;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
import com.facts.catfacts.services.FactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddFactUseCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FactService factService;

    @Autowired
    private FactRepository repository;

    @Test
    void addFactWorksThroughAllLayers() throws Exception {

        Fact requestBodyFact = new Fact("blank");

        MvcResult mvcResult = mockMvc.perform(post("/facts/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBodyFact)))
                .andExpect(status().isCreated()).andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        Fact persistedFact = objectMapper.readValue(result, Fact.class);

        Optional<Fact> fact = factService.getFact(persistedFact.getId());

        Assertions.assertEquals(persistedFact.getId(), fact.get().getId().longValue());
    }

    @AfterEach
    public void cleanUp() {
        repository.deleteAll();
    }
}
