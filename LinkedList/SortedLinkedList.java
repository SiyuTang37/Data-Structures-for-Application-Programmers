/**
 * the SortedLinkedList has the property of LinkedList but in a sorted way.
 * andrewid: siyut
 * @author Siyu Tang
 */
public class SortedLinkedList implements MyListInterface {
    /**
     * the head reference.
     */
    private Node head;
    /**
     * the nested class of node.
     */
    private static class Node {
        /**
         * the data of the node.
         */
        private String data;
        /**
         * the reference of the next node.
         */
        private Node next;
        /**
         * the constructor of the node.
         * @param d the string type data.
         * @param n the reference of the next node.
         */
        Node(String d, Node n) {
            data = d;
            next = n;
        }
    }
    /**
     * the no-arg constructor.
     */
    public SortedLinkedList() {
        head = null;
    }
    /**
     * the constructor with a parameter.
     * @param unsorted the unsorted string array
     */
    public SortedLinkedList(String[] unsorted) {
        //head = null;
        addList(unsorted, 0);
    }
    /**
     * a recursive method to copy the elements from the array
     * to the SortedLinkedList.
     * 1. The base case: when the loop variable index is greater and
     * equal than the length of the array, then stop adding.
     * 2. The Recursive case: when the index is less than
     * the length, then continue adding the next elements in the array
     * @param unsorted the string array
     * @param index the index of the array
     */
    private void addList(String[] unsorted, int index) {
        if (unsorted == null) {
            return;
        }
        if (index >= unsorted.length) {
            return;
        } else {
            add(unsorted[index]);
            addList(unsorted, index + 1);
        }
    }
    /**
     * sort when adding.
     * 1.0 base case: when n is the last node, add-last.
     * 1.1 base case: when n.next.data is bigger than the value, then insert the value
     *     after the n.
     * 1.2 base case: when n.next.data is equal to the value, then return to avoid inserting.
     * 2.0 recursive case: sort and add the next node.
     * @param n the current node reference
     * @param value the value to be added
     */
    private void sortAdd(Node n, String value) {
        if (n.next == null) { // && value.compareTo(n.data) != 0
            n.next = new Node(value, null);
            return;
        } else if (value.compareTo(n.next.data) < 0) {
            n.next = new Node(value, n.next);
            return;
        } else if (value.compareTo(n.next.data) == 0) {
            return;
        } else {
            sortAdd(n.next, value);  //stack overflow
        }
    }
    /**
     * Inserts a new String.
     * @param value String to be added.
     */
    @Override
    public void add(String value) {
            if (value == null) {
                return;
            }
            if (head == null) {
                head = new Node(value, null);
            } else {
                if (value.compareTo(head.data) < 0) {
                    Node n = new Node(value, head);
                    head = n;
                } else if (value.compareTo(head.data) == 0) {
                    return;
                } else {
                    sortAdd(head, value);
                }
            }
    }
    /**
     * Checks the size (number of data items) of the list.
     * 1.0 base case: when n is the next node of the last node indicating the end of the traversing.
     * 2.0 recursive case: calculating the size start from the next node plus 1.
     * @param n the starting node of the list
     * @return the size of the list
     */
    private int getSize(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + getSize(n.next);
    }
    /**
     * Wrapper function of the getSize.
     * @return the size of the list
     */
    @Override
    public int size() {
        return getSize(head);
    }
    /**
     * Displays the values of the list.
     * 1.0 base case: when n reaches the next node of the last node, the function returns.
     * 2.0 recursive case: print out the data in different forms depending on
     *     the position of the element in the list. Then print out the next node.
     * @param n the node to be printed
     */
    private void getDisplay(Node n) {
        if (n == null) {
            return;
        }
        if (n == head && n.next != null) {
            System.out.print("[" + n.data + ", ");
        } else if (n == head && n.next == null) {
            System.out.print("[" + n.data + "]");
        } else if (n != head && n.next != null) {
            System.out.print(n.data + ", ");
        } else if (n != head && n.next == null) {
            System.out.print(n.data + "]");
        }
            getDisplay(n.next);
    }
    /**
     * wrapper function of the getDisplay.
     */
    @Override
    public void display() {
        getDisplay(head);
        System.out.println();
    }
    /**
     * Returns true if the key value is in the list.
     * 1.0 base case: when n reaches the next node of the last node,
     *     return false indicating not found.
     * 1.1 base case: when key is found, return true.
     * 2.0 recursive case: search the list starting from the next node.
     * @param n the starting node for searching
     * @param key String key to search
     * @return true if found, false if not found
     */
    public boolean getContains(Node n, String key) {
        if (n == null) {
            return false;
        } else if (n.data.equals(key)) {      //string should use equals not "=="!
            return true;
        }
        return getContains(n.next, key);
    }
    /**
     * Wrapper function of the getContains.
     * @param key String key to search
     * @return true if found, false if not found
     */
    @Override
    public boolean contains(String key) {
        return getContains(head, key);
    }
    /**
     * Returns true is the list is empty.
     * @return true if it is empty, false if it is not empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }
    /**
     * Removes and returns the first String object of the list.
     * @return String object that is removed
     */
    @Override
    public String removeFirst() {
        if (head == null) {
            return null;
        }
        String tmp = head.data;
        head = head.next;
        return tmp;
    }
    /**
     * Removes and returns String object at the specified index.
     * 1.0 base case: when the index equals to 0, return removeFirst().
     * 1.1 base case: when the index is greater or equal to 1 and the next node
     *     is not null, the function return null because the value of the index
     *     is not found.
     * 1.2 base case: the index equals to 1 indicating that the next node should
     *     be the target value. Then, remove the next node and return its data.
     * 2.0 Recursive case: decrease the index and traverse the list starting from the next node.
     * @param n starting node for searching the index
     * @param index index to remove String object
     * @return String object that is removed
     */
    private String getRemoveAt(Node n, int index) {
  /*      if (index == 0) { //remove first
            removeFirst();
        } else if (n.next == null) {
            return null;
        } else if (index == 1) {
            Node tmp = n.next;
            n.next = n.next.next;
            return tmp.data;
        }
            return getRemoveAt(n.next, index - 1); */
        if (index == 0) {
            return removeFirst();  //此处错误： removefirst虽然return,但并没有return出getremoeat.
        }
        if (index != 0 && n.next == null) {
             return null;
        }
        if (index == 1) {
        //     Node tmp = n.next;
            String tmp = n.next.data;
             n.next = n.next.next;
             return tmp;
        }
            return getRemoveAt(n.next, index - 1);
    }
    /**
     * wrapper function of the getRemoveAt.
     * @param index index to remove String object
     * @return String object that is removed
     */
    @Override
    public String removeAt(int index) {
        if (head == null) {
            return null;
        }
        if (index < 0 || index >= size()) {
            return null;
        }
        return getRemoveAt(head, index);
    }
}
