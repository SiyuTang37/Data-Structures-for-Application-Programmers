/**
 * My array.
 * andrew id: siyut
 * @author Siyu Tang
 */
public class MyArray {
    /**
     * an array of object which stores the element of myArray.
     */
    private String[] array;
    /**
     * the size of the array.
     */
    private int size;
    /**
     * a no-arg constructor for MyArray.
     */
    public MyArray() {
        this(10);
    }
    /**
     * a constructor of MyArray.
     * @param initialCapacity
     * initial capacity of the array
     */
    public MyArray(int initialCapacity) {
        array = new String[initialCapacity];
    }
    /**
     * ensure that the array can hold at least the number of elements.
     * @param minCapacity the minimum capacity of the array to hold another element
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = getCapacity();
        if (minCapacity > oldCapacity) {
            int newCapacity;
            if (oldCapacity == 0) {
                newCapacity = 1;
            } else {
                newCapacity = 2 * oldCapacity;
            }
            String[] tmp = new String[newCapacity];
            System.arraycopy(array, 0, tmp, 0, size);
            array = tmp;
        }
    }
    /**
     * adding text to MyArray.
     * @param text the string to be added
     */
    public void add(String text) {
        ensureCapacity(size + 1);
        //null, empty string: "", numbers
        if ((text != null) && (text.length() != 0)) {//注意要把text != null放在text.length()前面判断，以免出现nullpointer错误。
            int i;
            for (i = 0; i < text.length(); i++) {
                boolean a = (text.charAt(i) >= 'a') && (text.charAt(i) <= 'z');
                boolean b = (text.charAt(i) >= 'A') && (text.charAt(i) <= 'Z');
                if (!(a || b)) {
                    break;
                }
            }
            if (i == text.length()) {
                array[size] = text;
                size++;
            }
        }
    }
    /**
     * search a string specified by key.
     * @param key
     * the source to be searched
     * @return
     * return boolean value indicating whether the key has been successfully searched(true if success)
     */
    public boolean search(String key) {
        boolean a = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(key)) {
                a = true;
                break;
            }
        }
        return a;
    }
    /**
     * calculating the number of elements.
     * @return
     * return the number of elements in my array
     */
    public int size() {
        return size;
    }
    /**
     * the capacity of the array.
     * @return
     * return the capacity of MyArray
     */
    public int getCapacity() {
        return array.length;
    }
    /**
     * display MyArray.
     */
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
/*  wrong:
        for (String s: array) {
            if (s != null) {       //不能用这个条件，因为在removedups函数里改变了array，size之外还有多余的string和null。所以要用现在的size判断而不是null判断。
                System.out.print(s + " ");
            }
        }*/
        System.out.println();
    }
    /**
     * remove one element from the array.
     * @param pos the index of the element
     */
    public void remove(int pos) {
        if (pos >= 0 && pos < size) {
            System.arraycopy(array, pos + 1, array, pos, size - pos - 1);
            array[size - 1] = null;
        }
    }
    /**
     * remove all of the duplicates in the words array.
     */
    public void removeDups() {
        int count = 0;
        int countSize = size;
        for (int i = 0; i < size - 1; i++) {
            for (int k = i + 1; k < size; k++) {
                if ((array[k] != null) && (array[k].equals(array[i]))) {//注意要加上array[k] != null这句，不然后边的equal判断会有nullpoint错误。
                    array[k] = null;
                }
            }
        }
        for (int j = 0; j < size; j++) {
            if (array[j] != null) {
                array[count] = array[j];
                count++;
            } else {
                countSize--;
            }
        }
        size = countSize;
    }
}
