package be.vdab.beers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BierNietGevondenException extends RuntimeException{
    private final long serialVersionUID = 1L;

    public BierNietGevondenException(long id){
        super("Bier niet gevonden. Id: " + id);
    }
}
