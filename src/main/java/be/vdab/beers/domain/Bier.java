package be.vdab.beers.domain;

import java.math.BigDecimal;

public class Bier {
    private final long id;
    private final String naam;
    private final long brouwerId;
    private final BigDecimal alcohol;
    private final BigDecimal prijs;
    private int besteld;

    public Bier(long id, String naam, long brouwerId, BigDecimal alcohol, BigDecimal prijs, int besteld) {
        this.id = id;
        this.naam = naam;
        this.brouwerId = brouwerId;
        this.alcohol = alcohol;
        this.prijs = prijs;
        this.besteld = besteld;
    }
    public int verhoogBesteld(){
        return besteld += 1;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getBrouwerId() {
        return brouwerId;
    }

    public BigDecimal getAlcohol() {
        return alcohol;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getBesteld() {
        return besteld;
    }
}
