package com.wordgame.backend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Jumbles")
public class JumblesController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/RandomGame")
    public ResponseEntity<String> RandomWordLadderGame(@RequestParam int difficulty) {
        try (Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call sp_GetRandomJumblesGame(?)}")) {
             callableStatement.setInt(1, difficulty);

             // Execute the stored procedure
             ResultSet rs = callableStatement.executeQuery();

            // Process JSON result set
            if (rs.next()) {
                var jsonResult = rs.getString(1);
                return ResponseEntity.ok(jsonResult);
            }
            else {
                return  ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SQL Error occurred: " + e.getMessage());
        }
    }
}
