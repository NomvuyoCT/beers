package be.vdab.beers.dto;


import be.vdab.beers.domain.Bestelling;
import be.vdab.beers.domain.Bier;
import jakarta.validation.Valid;

import java.util.List;

public record KlantEnBieren(@Valid Bestelling bestelling, @Valid List<Bier> bieren) {
}
