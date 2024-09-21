/** Base class for all games. */
abstract public class GameModel {
    /** Primary key for the game table. */
    public int Id;
    /** Difficulty for the instance of a game. */
    public GameDifficulty Difficulty;
    /** Holds the words for the game. */
    public GameConfigModel GameConfig = new GameConfigModel();
}
