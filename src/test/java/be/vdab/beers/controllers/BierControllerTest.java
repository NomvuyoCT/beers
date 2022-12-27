package be.vdab.beers.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql({"/brouwers.sql", "/bieren.sql"})
@AutoConfigureMockMvc
class BierControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
        private final String BIEREN = "bieren";

        private final MockMvc mockMvc;

    public BierControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    private long idVanTestBrouwer(){
        return jdbcTemplate.queryForObject(
                "select id from brouwers where naam  = 'testnaam1' ", Long.class
        );
    }
    private long idVanTestBier(){
        return jdbcTemplate.queryForObject(
                "select id from bieren where naam = 'testnaam1'", Long.class
        );
    }
    private int totaalAantalBieren(){
        return jdbcTemplate.queryForObject(
                "select count(id) from bieren", Integer.class
        );
    }

    @Test
    void aantalBieren() throws Exception {
        var aantalBieren = totaalAantalBieren();
        mockMvc.perform(get("/bieren"))
                .andExpectAll(
                        status().isOk());
        assertThat(countRowsInTable(BIEREN)).isEqualTo(aantalBieren);
    }

    @Test
    void findBierenByBrouwerId() throws Exception {
        var brouwerId = idVanTestBrouwer();
        mockMvc.perform(get("/bieren/vanBrouwer/{brouwerId}", brouwerId))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()").value(countRowsInTableWhere(BIEREN,
                                "brouwerId = " + brouwerId
                        ))
                );
    }

    @Test
    void findBierById() throws Exception {
        var id = idVanTestBier();
        mockMvc.perform(get("/bieren/{id}", id))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("id").value(id),
                        jsonPath("naam").value("testnaam1")
                );
    }

    @Test
    void findBierByOnbestaandeIdMislukt() throws Exception {
        mockMvc.perform(get("/bieren/{id}", Long.MAX_VALUE))
                .andExpect(
                        status().isNotFound()
                );
    }
}