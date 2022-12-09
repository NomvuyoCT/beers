package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bestelling;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class BestelRepository {
    private final JdbcTemplate template;

    public BestelRepository(JdbcTemplate template) {
        this.template = template;
    }
    public long create(Bestelling bestelling){
        var sql = """
                insert into bestellingen(naam, straat, huisnummer, postcode, gemeente)
                values(?, ?, ?, ?, ?)
                """;
        var keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            var statement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, bestelling.getNaam());
            statement.setString(2, bestelling.getStraat());
            statement.setString(3, bestelling.getHuisnummer());
            statement.setInt(4, bestelling.getPostcode());
            statement.setString(5, bestelling.getGemeente());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
