package be.vdab.beers.controllers;

import be.vdab.beers.domain.Bier;
import be.vdab.beers.services.BierService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@Validated
@RequestMapping("bieren")
public class BierController {
    private final BierService bierService;

    public BierController(BierService bierService) {
        this.bierService = bierService;
    }

    @GetMapping
    int aantalBieren(){
        return bierService.aantalBieren();
    }
    @GetMapping("vanBrouwer/{brouwerId}")
    Stream<Bier> findBierenByBrouwerId(@PathVariable long brouwerId){
        return bierService.findBierenByBrouwerId(brouwerId)
                .stream();
    }
    @GetMapping("{id}")
    Bier findBierById(@PathVariable long id){
        return bierService.findBierById(id);
    }
}
