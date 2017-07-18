import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
/**
 * building an index tree using the following three different methods.
 * @author Siyu Tang
 * andrew id: siyut
 */
public class Index {
    /**
     * pares an input text file and build an index tree
     * using a natural alphabetical order.
     * @param fileName the file name
     * @return return the BST of word
     */
    public BST<Word> buildIndex(String fileName) {
        if (fileName == null) {
            return null;
        }
        BST<Word> alphaBST = new BST<Word>();
        List<Word> list = readFile(fileName, null);
        for (Word w: list) {
            alphaBST.insert(w);
        }
        return alphaBST;
    }
    /**
     * parses in input text file.
     * @param fileName the file to be parsed
     * @param comparator a specific comparator to be used
     * @return return a list of Words
     */
    private List<Word> readFile(String fileName, Comparator<Word> comparator) {
        List<Word> list = new ArrayList<Word>();
        Scanner scanner = null;
        int numOfLines = 0;
        int flag = 0;
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");
                numOfLines++;
                for (String word : wordsFromText) {
                    if (word != null && isWord(word)) {
                        if (comparator instanceof IgnoreCase) {
                            word = word.toLowerCase();
                        }
                       for (Word w: list) {
                            if (w.getWord().equals(word)) {
                                w.setFrequency(w.getFrequency() + 1);
                                w.addToIndex(numOfLines);
                                flag = 1; //find it
                                break;
                            }
                        }
                        if (flag == 0) { //not found
                            Word newWord = new Word(word);
                            newWord.setFrequency(1);
                            newWord.addToIndex(numOfLines);
                            list.add(newWord);
                        }
                    }
                    flag = 0;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return list;
    }
    /**
     * build an index tree using a specific ordering provided by a comparator.
     * @param fileName the file to be parsed
     * @param comparator a specific comparator to be used
     * @return return the BST of word
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        if (fileName == null) {
            return null;
        }
        BST<Word> comBST = new BST<Word>(comparator);
        List<Word> list = readFile(fileName, comparator);
        for (Word w: list) {
            comBST.insert(w);
        }
        return comBST;
    }
    /**
     * rebuild an index tree using a different ordering specified by a comparator.
     * @param comparator a specific comparator to be used
     * @param list the list of words.
     * @return return the BST of word
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> reComBST = new BST<Word>(comparator);
        if (list == null) {
            return reComBST;
        }
        for (Word w: list) {
            reComBST.insert(w);
        }
        return reComBST;
    }
    /**
     * sort the BST by alphabet.
     * @param tree the BST to be sorted
     * @return an arrayList sorted by alphabet
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        if (tree == null) {
            return null;
        }
        ArrayList<Word> list = new ArrayList<Word>();
        Iterator<Word> itr = tree.iterator();
        while (itr.hasNext()) {
       //     System.out.println(itr.next().getWord()); //empty stack exception
            list.add(itr.next());
        }
        list.sort(new AlphaFreq());
        return list;
    }
    /**
     * sort the BST by frequency.
     * @param tree the BST to be sorted
     * @return an arrayList sorted by frequency
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        if (tree == null) {
            return null;
        }
        ArrayList<Word> list = new ArrayList<Word>();
        Iterator<Word> itr = tree.iterator();
        while (itr.hasNext()) {
            list.add(itr.next());
        }
        list.sort(new Frequency());
        return list;
    }
    /**
     * get the highest frequency of the words in the tree.
     * @param tree the tree to be searched
     * @return the list of words with the highest frequency
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        if (tree == null) {
            return null;
        }
        ArrayList<Word> sortedList = sortByFrequency(tree);
        ArrayList<Word> list = new ArrayList<Word>();
        int freq = sortedList.get(0).getFrequency();
        for (Word w: sortedList) {
            if (w.getFrequency() == freq) {
                list.add(w);
            } else {
                break;
            }
        }
        return list;
    }
    /**
     * Simple private helper method to validate a word.
     * @param text text to check
     * @return true if valid, false if not
     */
    private boolean isWord(String text) {
        return text.matches("[a-zA-Z]+");
    }

}
