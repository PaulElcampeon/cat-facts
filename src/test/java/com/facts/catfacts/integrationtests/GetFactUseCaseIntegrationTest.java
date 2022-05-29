package com.facts.catfacts.integrationtests;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
import com.facts.catfacts.services.FactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetFactUseCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FactService factService;

    @Autowired
    private FactRepository repository;

    @Test
    void getFactThatIsPresentWorksThroughAllLayers() throws Exception {
        //when
        Fact fact = new Fact("blank");
        factService.addFact(fact);

        MvcResult mvcResult = mockMvc
                .perform(get("/facts/{id}", fact.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String result = mvcResult.getResponse().getContentAsString();

        Fact retrievedFact = objectMapper.readValue(result, Fact.class);

        assertEquals(fact, retrievedFact);
    }

    @Test
    void getFactWorksThroughAllLayers() throws Exception {
        System.out.println(repository.count());
        //when
        Fact fact = new Fact("blank");
        factService.addFact(fact);

        MvcResult mvcResult = mockMvc
                .perform(get("/facts/{id}", fact.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String result = mvcResult.getResponse().getContentAsString();

        Fact retrievedFact = objectMapper.readValue(result, Fact.class);

        assertEquals(fact, retrievedFact);
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
    }
}
