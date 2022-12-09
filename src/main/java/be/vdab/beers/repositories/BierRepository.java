package be.vdab.beers.repository;

import be.vdab.beers.domain.Bier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BierRepository {
    private final JdbcTemplate template;

    public BierRepository(JdbcTemplate template) {
        this.template = template;
    }
    private RowMapper<Bier> bierRowMapper = (result, rowNum) ->
            new Bier(result.getLong("id"), result.getString("naam"),
                    result.getLong("brouwerId"), result.getBigDecimal("alcohol"),
                    result.getBigDecimal("prijs"), result.getInt("besteld"));
    public int aantalBieren(){
        var sql = """
                select count(id)
                from bieren
                """;
        return template.queryForObject(sql, Integer.class);
    }
    public List<Bier> findBierenByBrouwerId(long brouwerId){
        var sql = """
                select id, naam, brouwerId, alcohol, prijs, besteld
                from bieren
                where brouwerId = ?
                order by naam asc
                """;
        return template.query(sql, bierRowMapper, brouwerId);
    }
    public Optional<Bier> findBierById(long id){
        try {
            var sql = """
                select id, naam, brouwerId, alcohol, prijs, besteld
                from bieren
                where id = ?
                """;
            return Optional.of(template.queryForObject(sql, bierRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }
    public void updateBieren(int aantal, long id){
        var sql = """
                update bieren
                set besteld = ?
                where id = ?
                """;
        template.update(sql, aantal, id);
    }
}
