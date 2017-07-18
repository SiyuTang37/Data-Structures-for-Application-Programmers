import java.util.Comparator;
/**
 * sorts words by case insensitive alphabetical order.
 * @author Siyu Tang
 * andrew ID: siyut
 */
public class IgnoreCase implements Comparator<Word> {
    /**
     * the compare method.
     * @param o1 the word to be compared
     * @param o2 the word to be compared
     * @return the results of the comparing.
     */
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().compareToIgnoreCase(o2.getWord());
    }
}
