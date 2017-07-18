/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID: siyut
 * @author Siyu Tang
 */
public class MyHashTable implements MyHTInterface {
    /**
     * the loadFactor.
     */
    private double loadFactor;
    /**
     * the temporary variable to save the frequency during rehashing.
     */
    private int temFreq = 1;
    /**
     * the number of elements in the hashArray.
     */
    private int size = 0;
    /**
     * the number of collisions.
     */
    private int collisionsNum = 0;
    /**
     * the array of hash table.
     */
    private DataItem[] hashArray;
    /**
     * the size of the array.
     */
    private int arraySize;
    /**
     * the constant value of the deleted cell.
     */
    private static final DataItem DELETE = new DataItem("#DEL#");
    /**
     * implement constructor with no initial capacity.
     */
    public MyHashTable() {
        this(10);
    }
    /**
     * implement constructor with initial capacity.
     * @param initialCapacity the initial capacity of the hash table
     */
    public MyHashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new RuntimeException();
        }
        arraySize = initialCapacity;
        hashArray = new DataItem[arraySize];
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        int hashVal = input.charAt(0) - 96;
        for (int i = 1; i < input.length(); i++) {
            int number = input.charAt(i) - 96;
            hashVal = (hashVal * 27 + number) % arraySize;
        }
        return hashVal;
    }
    /**
     * check if it's a prime number.
     * @param sizeT the number to be checked
     * @return return true if the number is a prime.
     */
    private boolean isPrime(int sizeT) {
        for (int i = 2; i * i < sizeT; i++) {
            if (sizeT % i == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * find the next prime number after size.
     * @param sizeT the non-prime number
     * @return return the next prime number
     */
    private int nextPrime(int sizeT) {
        for (int i = sizeT + 1; true; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }
    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        collisionsNum = 0;
        int tmp = size;
        size = 0;
        int tmpSize = arraySize;
        DataItem[] tmpHashArray = hashArray;
        arraySize = nextPrime(2 * arraySize);
        hashArray = new DataItem[arraySize];
        System.out.println("Rehashing " + tmp + " items, new size is " + arraySize);
        for (int i = 0; i < tmpSize; i++) {
            if (tmpHashArray[i] != null && tmpHashArray[i] != DELETE) {
                temFreq = tmpHashArray[i].frequency;
                insert(tmpHashArray[i].value);
            }
        }
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;
        /**
         * the constructor of DataItem.
         * @param s the value of string
         */
        DataItem(String s) {
            value = s;
            frequency = 1;
        }
        /**
         * display the item.
         */
        private void displayItem() {
            System.out.print("[" + value + ", " + frequency + "] ");
        }
    }
    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    @Override
    public void insert(String value) {
        if (value == null || value.length() == 0 || !value.matches("[a-zA-Z]+")) {
            return;
        }
        DataItem item = new DataItem(value);
        item.frequency = temFreq;
        int hashVal = hashFunc(value);
        if (contains(value)) {
            while (!hashArray[hashVal].value.equals(value)) {
                hashVal++;
                hashVal = hashVal % arraySize;
            }
            hashArray[hashVal].frequency++;
            return;
        } else if (hashArray[hashVal] != null && hashArray[hashVal] != DELETE) {
            if (hashFunc(hashArray[hashVal].value) == hashVal) {
                collisionsNum++;
            }
        }
        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETE) {
            hashVal++;
            hashVal = hashVal % arraySize;
        }
        hashArray[hashVal] = item;
        size++;
        loadFactor = (double) size / arraySize;
        if (loadFactor > 0.5) {
            rehash();
        }
    }

    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    @Override
    public void display() {
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] == null) {
                System.out.print("** ");
            } else if (hashArray[i] == DELETE) {
                System.out.print("#DEL# ");
            } else {
                hashArray[i].displayItem();
            }
        }
        System.out.println();
    }

    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    @Override
    public boolean contains(String key) {
        int hashValue = hashFunc(key);
        if (hashValue > 0 && hashValue < arraySize) {
            while (hashArray[hashValue] != null) {
                if (hashArray[hashValue].value.equals(key)) {
                    return true;
                }
                hashValue++;
                hashValue = hashValue % arraySize;
            }
        }
        return false;
    }

    /**
     * Returns the number of collisions in relation to insert and rehash.
     * @return number of collisions
     */
    @Override
    public int numOfCollisions() {
        return collisionsNum;
    }

    /**
     * Returns the hash value of a String.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    @Override
    public int hashValue(String value) {
        return hashFunc(value);
    }

    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    @Override
    public int showFrequency(String key) {
        if (contains(key)) {
            int hashVal = hashFunc(key);
            while (!hashArray[hashVal].value.equals(key)) {
                hashVal++;
                hashVal = hashVal % arraySize;
            }
            return hashArray[hashVal].frequency;
        }
        return 0;
    }

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    @Override
    public String remove(String key) {
        int hashValue = hashFunc(key);
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].value.equals(key)) {
                DataItem tmp = new DataItem(key);
                hashArray[hashValue] = DELETE;
                size--;
                return tmp.value;
            }
            hashValue++;
            hashValue = hashValue % arraySize;
        }
        return null;
    }
}
