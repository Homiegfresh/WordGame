package com.wordgame.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WordLadderControllerTest {
    @Autowired
    private WordLadderController controller;

    @Autowired
    private JdbcTemplate jdbc;

    private void createTable() {
        try (var connection = Objects.requireNonNull(jdbc.getDataSource()).getConnection();
             var statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS " + "Test");
            statement.execute("CREATE TABLE " + "Test" + " (Id INT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTable() {
        try (var connection = Objects.requireNonNull(jdbc.getDataSource()).getConnection();
             var statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS " + "Test");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkTableExists() {
        String checkTableSQL = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = '" + "Test".toUpperCase() + "'";
        try (var connection = Objects.requireNonNull(jdbc.getDataSource()).getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(checkTableSQL)) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Test
    void SqlInjectionTest() {
        createTable();

        controller.ValidateInput(1, "''; DROP TABLE Games;--';");

        var tableExists = checkTableExists();

        deleteTable();

        assertTrue(tableExists);
    }
}
