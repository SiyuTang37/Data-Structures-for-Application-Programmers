import java.util.HashSet;
import java.util.Set;
/**
 * @author Siyu Tang
 * andrew id: siyut
 */
public class Word implements Comparable<Word> {
    /**
     * the string of the word.
     */
    private String word;
    /**
     * a set of line numbers of the word.
     */
    private Set<Integer> index;
    /**
     * the frequency of the word.
     */
    private int frequency;
    /**
     * the constructor of the Word class.
     * @param s the string of the word
     */
    public Word(String s) {
        word = s;
        index = new HashSet<Integer>();
        frequency = 1;
    }
    /**
     * the getter method of word.
     * @return return the word
     */
    public String getWord() {
        return word;
    }
    /**
     * the getter of frequency.
     * @return return the frequency of the word
     */
    public int getFrequency() {
        return frequency;
    }
    /**
     * the setter of the word.
     * @param s the string to be set to word
     */
    public void setWord(String s) {
        word = s;
    }
    /**
     * the setter of the word.
     * @param freq the string to be set to word
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }
    /**
     * add the line number to the index.
     * @param line the line number of the word
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }
    /**
     * the set of the index of the word.
     * @return a set of line number of the word
     */
    public Set<Integer> getIndex() {
        Set<Integer> copy = new HashSet<Integer>();
        copy.addAll(index);
        return copy;
    }
    /**
     * toString method of the Word class.
     * @return the string representation of the Word
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(word);
        s.append(" ");
        s.append(frequency);
        s.append(" ");
        s.append(index.toString());
        return s.toString();
    }
    /**
     * the compareTo methods.
     * @param o the word to be compared
     * @return the results of the comparing.
     */
    @Override
    public int compareTo(Word o) {
        return this.getWord().compareTo(o.getWord());
    }
}
