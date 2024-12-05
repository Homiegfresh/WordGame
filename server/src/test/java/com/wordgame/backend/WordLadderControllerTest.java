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

    private void createTable(String tableName) {
        try (var connection = Objects.requireNonNull(jdbc.getDataSource()).getConnection();
             var statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS " + tableName);
            statement.execute("CREATE TABLE " + tableName + " (Id INT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTable(String tableName) {
        try (var connection = Objects.requireNonNull(jdbc.getDataSource()).getConnection();
             var statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkTableExists(String tableName) {
        String checkTableSQL = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = '" + tableName.toUpperCase() + "'";
        try (var connection = Objects.requireNonNull(jdbc.getDataSource()).getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(checkTableSQL)) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Test
    void validateInput() {
        createTable("Test");

        controller.ValidateInput(1, "''; DROP TABLE Games;--';");

        var tableExists = checkTableExists("Test");

        deleteTable("Test");

        assertTrue(tableExists);
    }
}
