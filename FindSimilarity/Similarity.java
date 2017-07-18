import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * choose Map interfaces implemented by HashMap.
 * purpose, when calculating similarity, there will be lots of searching.
 * the running time complexity of searching in HashMap is O(1), which is
 * the fastest comparing to array, linkedList and other data structure we
 * learned before.
 * @author Siyu Tang
 * andrewId: siyut
 */
public class Similarity {
    /**
     * the map of the similarity class.
     */
    private Map<String, BigInteger> map;
    /**
     * number of lines in the text.
     */
    private int numOfLines;
    /**
     * number of words in the text.
     */
    private BigInteger numOfWords;
    /**
     * the constructor.
     * @param string the String type of input
     */
    public Similarity(String string) {
        numOfLines = 0;
        numOfWords = BigInteger.ZERO;
        map = new HashMap<String, BigInteger>();
        if (string != null && map != null) {
            readString(string);
        }
    }
    /**
     * another constructor.
     * @param file the file type of input
     */
    public Similarity(File file) {
        numOfLines = 0;
        numOfWords = BigInteger.ZERO;
        map = new HashMap<String, BigInteger>();
        if (file != null && map != null) {
            readFile(file);
        }
    }
    /**
     * count the number of lines.
     * @return the number of lines
     */
    public int numOfLines() {
        return numOfLines;
    }
    /**
     * count the number of words in the text.
     * @return the number of words
     */
    public BigInteger numOfWords() {
        return numOfWords;
    }
    /**
     * count the number of words without duplicates.
     * @return the number of words without duplicates
     */
    public int numOfWordsNoDups() {
        if (map == null) {
            return 0;
        }
        return map.size();
    }
    /**
     * the wrapper method of euclideanNormHelp.
     * @return the length of the vector
     */
    public double euclideanNorm() {
        return euclideanNormHelp(map);
    }
    /**
     * calculate the length of the map.
     * @param mp the given vector to be calculated
     * @return the length of the vector
     */
    private double euclideanNormHelp(Map<String, BigInteger> mp) {
        if (mp == null) {
            return 0;
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger v2;
        for (BigInteger value: mp.values()) {
            v2 = value.multiply(value);
            result = result.add(v2);
        }
        return Math.sqrt(result.doubleValue());
    }
    /**
     * calculate the dot product of the map and the given map.
     * running time complexity
     * containsKey() method provides constant-time performance.
     * Assuming the number of keys in map is n, the running time complexity
     * is only O(n) on average because of the for loop.
     * @param mp the given map to be calculated with the map
     * @return the value of dot product
     */
    public double dotProduct(Map<String, BigInteger> mp) {
        if (mp == null || map == null) {
            return 0;
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger product = BigInteger.ZERO;
        for (String key: map.keySet()) {
            if (mp.containsKey(key)) {
                product = map.get(key).multiply(mp.get(key));
                result = result.add(product);
            }
        }
        return result.doubleValue();
    }
    /**
     * calculate the distance of the two maps.
     * @param mp the given map to be calculated
     * @return the value of distance between the two maps
     */
    public double distance(Map<String, BigInteger> mp) {
        if (mp == null || map == null) {
            return Math.PI / 2;
        }
        if (mp.isEmpty() || map.isEmpty()) {
            return Math.PI / 2;
        }
        if (map.equals(mp)) {
            return 0;
        }
        double eucli1 = euclideanNorm();
        if (eucli1 == 0) {
            return Math.PI / 2;
        }
        double eucli2 = euclideanNormHelp(mp);
        if (eucli2 == 0) {
            return Math.PI / 2;
        }
        double dotProduct = dotProduct(mp);
        double result = dotProduct / eucli1 / eucli2;
        return Math.acos(result);
    }
    /**
     * the getter of the map.
     * @return the map
     */
    public Map<String, BigInteger> getMap() {
        Map<String, BigInteger> copy = new HashMap<String, BigInteger>();
        copy.putAll(map);
        return copy;
    }
    /**
     * read the string to the map.
     * @param string the string to be read
     */
    void readString(String string) {
        String[] words = string.split("\\W");
        for (String word : words) {
            if (word != null && isWord(word)) {
                if (!map.containsKey(word.toLowerCase())) {
                    map.put(word.toLowerCase(), BigInteger.ONE);
                } else {
                    map.put(word.toLowerCase(), map.get(word.toLowerCase()).add(BigInteger.ONE));
                }
                numOfWords = numOfWords.add(BigInteger.ONE);
            }
        }
        numOfLines++;
    }
    /**
     * read the file.
     * @param file the file to be read
     */
    void readFile(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                    if (word != null && isWord(word)) {
                        if (!map.containsKey(word.toLowerCase())) {
                            map.put(word.toLowerCase(), BigInteger.ONE);
                        } else {
                            map.put(word.toLowerCase(), map.get(word.toLowerCase()).add(BigInteger.ONE));
                        }
                        numOfWords = numOfWords.add(BigInteger.ONE);
                    }
                }
                numOfLines++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
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
