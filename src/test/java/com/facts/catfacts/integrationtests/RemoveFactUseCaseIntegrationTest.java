package com.facts.catfacts.integrationtests;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
import com.facts.catfacts.services.FactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RemoveFactUseCaseIntegrationTest {

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
    void removeFactThatIsPresentWorksThroughAllLayers() throws Exception {
        //when
        Fact requestBodyFact = new Fact("blank");
        factService.addFact(requestBodyFact);

        mockMvc.perform(delete("/facts/{id}", requestBodyFact.getId()))
                .andExpect(status().isOk())
                .andReturn();

        //then
        Optional<Fact> retrievedFact = factService.getFact(requestBodyFact.getId());

        long count = factService.count();

        assertEquals(Optional.empty(), retrievedFact);

        assertEquals(0, count);
    }

    @Test
    void removeFactThatIsNotPresentWorksThroughAllLayers() throws Exception {
        //when
        mockMvc.perform(delete("/facts/{id}", 1l))
                .andExpect(status().isBadRequest())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmptyResultDataAccessException));
                .andExpect(result -> assertEquals(String.format("No %s entity with id %s exists!", Fact.class, 1l), result.getResponse().getErrorMessage()));
    }

    @AfterEach
    public void cleanUp() {
        repository.deleteAll();
    }
}

