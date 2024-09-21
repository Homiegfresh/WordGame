/** This class contains properties for words in the games. */
public class WordModel {
    /** Primary key for the word table. */
    public int Id;
    /** The property that holds the word as a string. */
    public String Word = "";
    /** A description or definition of the word. */
    public String Description = "";

    /** Constructor for the word model. */
    public WordModel(String word, String description) {
        Word = word;
        Description = description;
    }

    /** Method to fetch the length of the word. */
    public int GetWordLength() {
        return Word.length();
    }

    /** Method to get a character array of the word. */
    public char[] GetWordChars() {
        return Word.toCharArray();
    }
}
