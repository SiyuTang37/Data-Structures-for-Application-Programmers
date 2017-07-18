import java.util.Comparator;
/**
 * sorts words according to their frequencies.
 * @author  Siyu Tang
 * andrew ID: siyut
 */
public class Frequency implements Comparator<Word> {
    /**
     * the compare method.
     * @return the results of the comparing.
     * @param o1 the word to be compared
     * @param o2 the word to be compared
     */
    @Override
    public int compare(Word o1, Word o2) {
        return Integer.compare(o2.getFrequency(), o1.getFrequency());
    }
}
