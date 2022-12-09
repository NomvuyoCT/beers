package be.vdab.beers.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql("/brouwers.sql")
@AutoConfigureMockMvc
class BrouwerControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
        private final String BROUWERS = "brouwers";
        private final MockMvc mockMvc;

    public BrouwerControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void findAllBrouwers() throws Exception{
        mockMvc.perform(get("/brouwers"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()").value(countRowsInTable(BROUWERS))
                );
    }
}