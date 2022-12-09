package be.vdab.beers.controllers;

import be.vdab.beers.dto.KlantEnBieren;
import be.vdab.beers.services.BestelService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("bestellingen")
public class BestelController {
    private final BestelService bestelService;

    public BestelController(BestelService bestelService) {
        this.bestelService = bestelService;
    }
    @PostMapping
    long bestel(@RequestBody @Valid KlantEnBieren klantEnBieren){
        return bestelService.bestel(klantEnBieren);
    }
}
