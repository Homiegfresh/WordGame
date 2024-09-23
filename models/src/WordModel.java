/** This class contains properties for words in the games. */
public class WordModel {
    /** Primary key for the word table. */
    private int Id;
    /** The property that holds the word as a string. */
    private String Word = "";
    /** A description or definition of the word. */
    private String Description = "";

    // region Constructors
    public WordModel() {}

    /** Constructor for the word model. */
    public WordModel(String word, String description) {
        Word = word;
        Description = description;
    }
    // endregion

    // region Getters
    public int getId() { return Id; }

    public String getWord() { return Word; }

    public String getDescription() { return Description; }
    // endregion

    // region Setters
    public void setId(int id) { Id = id; }

    public void setWord(String word) { Word = word; }

    public void setDescription(String description) { Description = description; }
    // endregion

    /** Method to fetch the length of the word. */
    public int GetWordLength() {
        return Word.length();
    }

    /** Method to get a character array of the word. */
    public char[] GetWordChars() {
        return Word.toCharArray();
    }
}
