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
                var model = new WordLadderGameModel();

                var id = rs.getInt("Id");
                var firstWord = rs.getString("FirstWord");
                var lastWord = rs.getString("LastWord");

                model.SetId(id);
                model.setFirstWord(firstWord, null);
                model.setLastWord(lastWord, null);

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
}
