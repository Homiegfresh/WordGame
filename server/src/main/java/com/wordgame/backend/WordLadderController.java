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

/// This controller handles the backend operations of the Word Ladder game.
@RestController
@RequestMapping("/api/WordLadder")
public class WordLadderController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * This endpoint fetches a random word ladder game.
     * @param difficulty Difficulty of the game.
     * @return JSON for WordLadderModel.
     */
    @GetMapping("/RandomGame")
    public ResponseEntity<String> RandomWordLadderGame(@RequestParam int difficulty) {
        try (Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call sp_GetRandomWordLadderGame(?)}")) {
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

    /**
     * This endpoint validates the input of the user against the game they have been assigned.
     * @param gameId The ID of the game the player is playing.
     * @param word The word the player is trying to solve.
     * @return Returns a boolean as a string value.
     */
    @GetMapping("/ValidateInput")
    public ResponseEntity<String> ValidateInput(@RequestParam int gameId, @RequestParam String word) {
        try (Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call sp_WordLadderValidateInput(?, ?)}")) {
            callableStatement.setInt(1, gameId);
            callableStatement.setString(2, word);

            // Execute the stored procedure
            ResultSet rs = callableStatement.executeQuery();

            // Process JSON result set
            if (rs.next()) {
                var result = rs.getBoolean(1);
                return ResponseEntity.ok(result ? "true" : "false");
            }
            else {
                return ResponseEntity.ok("false");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SQL Error occurred: " + e.getMessage());
        }
    }
}
