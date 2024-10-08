package com.wordgame.backend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordgame.models.WordLadderGameModel;

@RestController
@RequestMapping("/api/WordLadder")
public class WordLadderController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public String GetAllWordLadderGames() {
        var result = "";
        try (Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call GetAllWordLadderGames}")) {

            // Execute the stored procedure
            ResultSet rs = callableStatement.executeQuery();

            // Process result set
            var output = new ArrayList<WordLadderGameModel>();
            while (rs.next()) {
                var model = new WordLadderGameModelMapper().mapRow(rs, 0);
                output.add(model);
            }

            var objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(output);

        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
            result = "Error occurred: " + e.getMessage();
        }
        return result;
    }

    private class WordLadderGameModelMapper implements RowMapper<WordLadderGameModel> {
        @Override
        public WordLadderGameModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            var wordLadder = new WordLadderGameModel();
            var id = rs.getInt("Id");
            var firstWord = rs.getString("FirstWord");
            var lastWord = rs.getString("LastWord");
            var hint = rs.getString("Hint");

            wordLadder.SetId(id);
            wordLadder.setFirstWord(firstWord, null);
            wordLadder.setLastWord(lastWord, hint);

            return wordLadder;
        }
    }
}
