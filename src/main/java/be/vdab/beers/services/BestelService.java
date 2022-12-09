package be.vdab.beers.services;

import be.vdab.beers.domain.Bestellijn;
import be.vdab.beers.dto.KlantEnBieren;
import be.vdab.beers.exceptions.BierNietGevondenException;
import be.vdab.beers.repositories.BestelRepository;
import be.vdab.beers.repositories.BestellijnRepository;
import be.vdab.beers.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BestelService {
    private final BestelRepository bestelRepository;
    private final BierRepository bierRepository;
    private final BestellijnRepository bestellijnRepository;

    public BestelService(BestelRepository bestelRepository, BierRepository bierRepository, BestellijnRepository bestellijnRepository) {
        this.bestelRepository = bestelRepository;
        this.bierRepository = bierRepository;
        this.bestellijnRepository = bestellijnRepository;
    }
    @Transactional
    public long bestel(KlantEnBieren klantEnBieren){
        var bieren = klantEnBieren.bieren();
        bieren.forEach(bier -> {
            var id = bier.getId();
            //bierRepository.findAndLockById(id).get();
            bierRepository.findAndLockById(id)
                            .orElseThrow(() -> new BierNietGevondenException(id));
            bier.verhoogBesteld();
            bierRepository.updateBieren(bier.getBesteld(), id);
        });
        var bestelling =  klantEnBieren.bestelling();
        var bestelId = bestelRepository.create(bestelling);
        bieren.forEach(bier -> {
            var bestellijn = new Bestellijn(bestelId, bier.getId());
            bestellijnRepository.create(bestellijn);
        });
        return bestelId;
    }
}
