package be.vdab.beers.repository;

import be.vdab.beers.domain.Bestellijn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BestellijnRepository {
    private final JdbcTemplate template;

    public BestellijnRepository(JdbcTemplate template) {
        this.template = template;
    }
    public void create(Bestellijn bestellijn){
        var sql = """
                insert into bestellijnen(bestelId, bierId) 
                VALUES(?,?)
                """;
        template.update(sql, bestellijn.getBestelId(), bestellijn.getBierId());
    }
}
