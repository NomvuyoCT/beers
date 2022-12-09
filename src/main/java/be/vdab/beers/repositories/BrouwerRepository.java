package be.vdab.beers.repository;

import be.vdab.beers.domain.Brouwer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrouwerRepository {
    private final JdbcTemplate template;

    public BrouwerRepository(JdbcTemplate template) {
        this.template = template;
    }
    private RowMapper<Brouwer> brouwerRowMapper = (result, rowNum) ->
            new Brouwer(result.getLong("id"), result.getString("naam"),
                    result.getString("straat"), result.getString("huisNr"),
                    result.getInt("postcode"), result.getString("gemeente"),
                    result.getInt("omzet"));
    public List<Brouwer> findAllBrouwers(){
        var sql = """
                select id, naam, straat, huisNr, postcode, gemeente, omzet
                from brouwers
                order by naam ASC
                """;
        return template.query(sql, brouwerRowMapper);
    }
}
