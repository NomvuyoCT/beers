package be.vdab.beers.service;

import be.vdab.beers.domain.Bier;
import be.vdab.beers.repository.BierRepository;
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
    public List<Bier> findBierenByBrouwerId(long brouwerId){
        return bierRepository.findBierenByBrouwerId(brouwerId);
    }
}
