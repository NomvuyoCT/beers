package be.vdab.beers.controllers;

import be.vdab.beers.domain.Bier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@Sql({"/bestellingen.sql", "/brouwers.sql", "/bieren.sql","/bestellijnen.sql"})
@AutoConfigureMockMvc
class BestelControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final String BESTELLINGEN = "bestellingen";
    private final String BESTELLIJNEN = "bestellijnen";
    private final String BIEREN = "bieren";
    private final static Path TEST_RESOURCES = Path.of("src/test/resources");
    private final MockMvc mockMvc;

    public BestelControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private Bier findTestBier(){
        return jdbcTemplate.queryForObject(
                "select id, naam, alcohol, prijs, besteld from bieren where naam = 'testnaam'",
                Bier.class
        );
    }

    @Test
    void bestel() throws Exception {
        var jsonData = Files.readString(TEST_RESOURCES.resolve("correcteBestelling.json"));
        mockMvc.perform(post("/bestellingen")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());
        assertThat(countRowsInTableWhere(BESTELLINGEN, "naam = 'testnaam'")).isOne();
        assertThat(countRowsInTableWhere(BESTELLIJNEN, "bierId = 7")).isOne();
        assertThat(countRowsInTableWhere(BIEREN, "naam = 'Aardbeien witbier' and besteld = 4")).isOne();

    }

    @ParameterizedTest
    @ValueSource(strings = {"bestellingZonderNaam.json", "bestellingMetLegeNaam.json", "bestellingZonderStraat.json",
    "bestellingMetLegeNaam.json", "bestellingZonderPostcode.json", "bestellingMetTeKleinePostcode.json",
            "bestellingMetTeGrotePostcode.json", "bestellingZonderGemeente.json", "bestellingMetLegeGemeente.json"})
    void bestelMetFouteDataMislukt(String bestandsnaam) throws Exception{
        var jsonData =
                Files.readString(TEST_RESOURCES.resolve(bestandsnaam));
        mockMvc.perform(post("/bestellingen")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isBadRequest());
    }



}