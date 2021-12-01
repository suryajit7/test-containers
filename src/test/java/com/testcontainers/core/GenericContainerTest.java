package com.testcontainers.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.testcontainers.core.entity.ComicUniversum;
import com.testcontainers.core.entity.Hero;
import com.testcontainers.core.repo.HeroClassicJDBCRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.startupcheck.IndefiniteWaitOneShotStartupCheckStrategy;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@Testcontainers
public class GenericContainerTest {

    @Container
    private final MySQLContainer database = new MySQLContainer("mysql")
            .withUsername("test")
            .withPassword("test");

    private HeroClassicJDBCRepository repositoryUnderTest;

    private DataSource dataSource;

    @BeforeAll
    public void beforeSetup(){
        database.withStartupCheckStrategy(new IndefiniteWaitOneShotStartupCheckStrategy());
        database.getLogs();
        dataSource = getDataSource();
    }

    @Test
    public void testInteractionWithDatabase() throws InterruptedException {

        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(database, ""),"data.sql");

        repositoryUnderTest = new HeroClassicJDBCRepository(dataSource);

        repositoryUnderTest.addHero(new Hero("Batman", "Gotham City", ComicUniversum.DC_COMICS));

        Collection<Hero> heroes = repositoryUnderTest.allHeros();

        assertThat(heroes).hasSize(1);
    }

    @NotNull
    private DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(database.getJdbcUrl());
        dataSource.setUser(database.getUsername());
        dataSource.setPassword(database.getPassword());
        return dataSource;
    }


}
