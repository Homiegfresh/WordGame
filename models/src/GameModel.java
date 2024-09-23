/** Base class for all games. */
abstract public class GameModel {
    /** Primary key for the game table. */
    private int Id;
    /** Difficulty for the instance of a game. */
    private GameDifficulty Difficulty = GameDifficulty.Unknown;
    /** Holds the words for the game. */
    private GameConfigModel GameConfig = new GameConfigModel();

    // region Getters
    public int GetId() { return Id; }
    public GameDifficulty GetDifficulty() { return Difficulty; }
    public GameConfigModel GetGameConfig() { return GameConfig; }
    // endregion

    // region Setters
    public void SetGameDifficulty(GameDifficulty difficulty) { Difficulty = difficulty; }
    public void SetGameConfig(GameConfigModel gameConfig) { GameConfig = gameConfig; }
    public void SetId(int id) { Id = id; }
    // endregion
}
