package com.wordgame;

import com.wordgame.models.WordLadderGameModel;
import com.wordgame.models.enums.GameDifficulty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class WordLadderControllerTest {
    private static final int THREAD_COUNT = 10;
    private static final int REQUEST_COUNT = 10;

    @Test
    void FetchDataTest() {
        var model = ServiceHelpers.GetRandomWordLadderGame(GameDifficulty.EASY);
        assertNotNull(model);
    }

    @Test
    void StressTest() throws ExecutionException, InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT)) {
            List<Future<WordLadderGameModel>> futures = new ArrayList<>();

            // List of responses that will come back from the requests.
            for (int i = 0; i < REQUEST_COUNT; i++) {
                for (int j = 0; j < THREAD_COUNT; j++) {
                    futures.add(executor.submit(() -> {
                        try {
                            return ServiceHelpers.GetRandomWordLadderGame(GameDifficulty.EASY);
                        }
                        catch (Exception e) {
                            return null;
                        }
                    }));
                }
            }

            // Wait for threads to finish.
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            // Ensure none of the responses are null.
            var outputModels = new ArrayList<WordLadderGameModel>();
            for (var wordLadderFuture : futures) {
                var model = wordLadderFuture.get();
                outputModels.add(model);
            }

            var containsANull = outputModels.stream().anyMatch(Objects::isNull);
            assertFalse(containsANull);
        }
    }
}