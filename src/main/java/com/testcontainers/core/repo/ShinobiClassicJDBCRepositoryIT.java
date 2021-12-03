package com.testcontainers.core.repo;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.testcontainers.core.entity.Shinobi;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.Collection;

import static com.testcontainers.core.entity.HiddenVillage.KIRIGAKURE;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class ShinobiClassicJDBCRepositoryIT {

    @Container
    private final MySQLContainer database = new MySQLContainer();

    private ShinobiClassicJDBCRepository repositoryUnderTest;

    @Test
    void testInteractionWithDatabase() {
        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(database, ""),"ddl.sql");
        repositoryUnderTest = new ShinobiClassicJDBCRepository(dataSource());

        repositoryUnderTest.addNinja(new Shinobi("Kisame Hoshigaki", "Jonin", KIRIGAKURE));

        Collection<Shinobi> shinobis = repositoryUnderTest.allShinobis();

        assertThat(shinobis).hasSize(1);
    }

    @NotNull
    private DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(database.getJdbcUrl());
        dataSource.setUser(database.getUsername());
        dataSource.setPassword(database.getPassword());
        return dataSource;
    }





}
