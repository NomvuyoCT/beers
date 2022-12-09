package be.vdab.beers.domain;

public class Bestellijn {
    private final long bestelId;
    private final long bierId;

    public Bestellijn(long bestelId, long bierId) {
        this.bestelId = bestelId;
        this.bierId = bierId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public long getBierId() {
        return bierId;
    }
}
