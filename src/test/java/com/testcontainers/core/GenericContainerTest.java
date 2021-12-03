package com.testcontainers.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.testcontainers.core.entity.HiddenVillage;
import com.testcontainers.core.entity.Shinobi;
import com.testcontainers.core.repo.ShinobiClassicJDBCRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.startupcheck.IsRunningStartupCheckStrategy;
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
    private final MySQLContainer database = new MySQLContainer("mysql");

    private ShinobiClassicJDBCRepository repositoryUnderTest;

    private DataSource dataSource;

    @BeforeAll
    public void beforeSetup(){
        database.withStartupCheckStrategy(new IsRunningStartupCheckStrategy());
        database.getLogs();
    }

    @Test
    public void testInteractionWithDatabase() throws InterruptedException {

        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(database, ""),"data.sql");

        repositoryUnderTest = new ShinobiClassicJDBCRepository(getDataSource());

        repositoryUnderTest.addNinja(new Shinobi("Batman", "Gotham City", HiddenVillage.KONOHAGAKURE));

        Collection<Shinobi> shinobis = repositoryUnderTest.allShinobis();

        assertThat(shinobis).hasSize(1);
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
