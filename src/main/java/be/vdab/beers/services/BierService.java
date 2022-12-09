package be.vdab.beers.services;

import be.vdab.beers.domain.Bier;
import be.vdab.beers.exceptions.BierNietGevondenException;
import be.vdab.beers.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BierService {
    private final BierRepository bierRepository;

    public BierService(BierRepository bierRepository) {
        this.bierRepository = bierRepository;
    }

    public int aantalBieren(){
        return bierRepository.aantalBieren();
    }
    public List<Bier> findBierenByBrouwerId(long brouwerId){
        return bierRepository.findBierenByBrouwerId(brouwerId);
    }
    public Bier findBierById(long id){
        var bier = bierRepository.findBierById(id)
                .orElseThrow(() -> new BierNietGevondenException(id));
        return bier;
    }
}
