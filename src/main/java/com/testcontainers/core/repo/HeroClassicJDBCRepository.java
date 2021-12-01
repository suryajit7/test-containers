package com.testcontainers.core.repo;

import com.testcontainers.core.entity.ComicUniversum;
import com.testcontainers.core.entity.Hero;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
public class HeroClassicJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    public HeroClassicJDBCRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addHero(Hero hero) {
        jdbcTemplate.update("insert into hero (city, name, universum) values (?,?,?)",
                hero.getCity(), hero.getName(), hero.getUniversum().name());

    }

    public Collection<Hero> allHeros() {
        return jdbcTemplate.query("select * From hero",
                (resultSet, i) -> new Hero(resultSet.getString("name"),
                        resultSet.getString("city"),
                        ComicUniversum.valueOf(resultSet.getString("universum"))));
    }


}
