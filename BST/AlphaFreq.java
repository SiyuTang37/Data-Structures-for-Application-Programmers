import java.util.Comparator;
/**
 * sorts words according to alphabets first and if there.
 * is a tie, then words are sorted by their frequencies
 * in ascending order
 * @author  Siyu Tang
 * andrew ID: siyut
 */
public class AlphaFreq implements Comparator<Word> {
    /**
     * the compare method.
     * @return the results of the comparing.
     * @param o1 the word to be compared
     * @param o2 the word to be compared
     */
    @Override
    public int compare(Word o1, Word o2) {
        int alphaResult = o1.compareTo(o2);
        if (alphaResult != 0) {
            return alphaResult;
        }
        return Integer.compare(o1.getFrequency(), o2.getFrequency());
    }
}
