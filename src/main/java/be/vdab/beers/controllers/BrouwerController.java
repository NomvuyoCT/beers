package be.vdab.beers.controllers;

import be.vdab.beers.domain.Brouwer;
import be.vdab.beers.services.BrouwerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@Validated
@RequestMapping("brouwers")
public class BrouwerController {
    private final BrouwerService brouwerService;

    public BrouwerController(BrouwerService brouwerService) {
        this.brouwerService = brouwerService;
    }
    @GetMapping
    Stream<Brouwer> findAllBrouwers(){
        return brouwerService.findAllBrouwers()
                .stream();
    }
}
