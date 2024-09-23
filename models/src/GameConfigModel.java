import java.util.ArrayList;
import java.util.List;

/** This class holds that generic configuration object for a game. */
public class GameConfigModel {
    /** Primary key in the database. */
    private int Id;
    /** Foreign key to the game table. */
    private int GameId;
    /** List of words that are to be used in the game. */
    private List<WordModel> GameWords = new ArrayList<WordModel>();

    // region Getters
    public int GetId() { return Id; }
    public int GetGameId() { return GameId; }
    public List<WordModel> GetGameWords() { return GameWords; }
    // endregion

    // region Setters
    public void SetId(int id) { Id = id; }
    public void setGameId(int gameId) { GameId = gameId; }
    public void setGameWords(List<WordModel> gameWords) { GameWords = gameWords; }
    // endregion

    /** Allows the system to add words to the game words list. */
    public void addGameWord(WordModel gameWord) {
        if (gameWord == null) return;
        GameWords.add(gameWord);
    }
}
