package be.vdab.beers.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Bestelling {
    private final long id;
    @NotBlank
    private final String naam;
    @NotBlank
    private final String straat;
    @NotBlank
    private final String huisnummer;
    @NotNull @Min(1000) @Max(9999)
    private final int postcode;
    @NotBlank
    private final  String gemeente;

    public Bestelling(long id, String naam, String straat, String huisnummer, int postcode, String gemeente) {
        this.id = id;
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }
    @JsonCreator
    public Bestelling(String naam, String straat, String huisnummer, int postcode, String gemeente){
        this(0, naam, straat, huisnummer, postcode, gemeente);
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
