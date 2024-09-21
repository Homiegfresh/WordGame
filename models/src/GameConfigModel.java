import java.util.ArrayList;
import java.util.List;

/** This class holds that generic configuration object for a game. */
public class GameConfigModel {
    /** Primary key in the database. */
    public int Id;
    /** Foreign key to the game table. */
    public int GameId;
    /** List of words that are to be used in the game. */
    public List<WordModel> GameWords = new ArrayList<WordModel>();
}
