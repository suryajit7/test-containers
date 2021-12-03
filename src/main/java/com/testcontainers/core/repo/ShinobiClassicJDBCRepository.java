package com.testcontainers.core.repo;

import com.testcontainers.core.entity.HiddenVillage;
import com.testcontainers.core.entity.Shinobi;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
public class ShinobiClassicJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShinobiClassicJDBCRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addNinja(Shinobi shinobi) {
        jdbcTemplate.update("insert into hero (rank, name, village) values (?,?,?)",
                shinobi.getRank(), shinobi.getName(), shinobi.getVillage().name());

    }

    public Collection<Shinobi> allShinobis() {
        return jdbcTemplate.query("select * From shinobi",
                (resultSet, i) -> new Shinobi(resultSet.getString("name"),
                        resultSet.getString("rank"),
                        HiddenVillage.valueOf(resultSet.getString("village"))));
    }


}
