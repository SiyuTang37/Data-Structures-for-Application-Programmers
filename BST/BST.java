import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
/**
 * build a BST structure.
 * @author elantang
 * andrew id: siyut
 * @param <T> any type
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * the root of the BST.
     */
    private Node<T> root;
    /**
     * the comparator of the BST.
     */
    private Comparator<T> comparator;
    /**
     * number of nodes in the BST.
     */
    private int numOfNodes;
    /**
     * the no-arg constructor of the BST class.
     */
    public BST() {
        this(null);
    }
    /**
     * the constructor.
     * @param comp the comparator of the BST
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }
    /**
     * get the comparator of the BST.
     * @return the comparator of the BST
     */
    public Comparator<T> comparator() {
        return comparator;
    }
    /**
     * getter of the root.
     * @return the data of the root
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }
        return root.data;
    }
    /**
     * wrapper method of heightHelper.
     * @return the height of the BST
     */
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return heightHelper(root);
    }
    /**
     * get the height of the given root.
     * base case: when node is null, return -1
     * @param node the new root of sub tree
     * @return return the height
     */
    private int heightHelper(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }
    /**
     * get the number of nodes in the BST.
     * @return number of nodes in the BST
     */
    public int getNumberOfNodes() {
        return numOfNodes;
    }
    /**
     * wrapper method of the searchHelp.
     * @param toSearch Object value to search
     * @return The value (object) of the search result
     */
    @Override
    public T search(T toSearch) {
        if (root == null) {
            return null;
        }
        int compareResult = 0;
        return searchHelp(root, toSearch, compareResult);
    }
    /**
     * Given the value (object), tries to find it.
     * base case: 1. when node is null, indicating not found.
     *            2. when key is matched, found successfully, return the node
     * @param node the current node
     * @param toSearch Object value to search
     * @param compareResult the comparing result
     * @return The value (object) of the search result. If not found, null.
     */
    private T searchHelp(Node<T> node, T toSearch, int compareResult) {
        if (node == null) {
            return null;
        }
        if (comparator == null) {
            compareResult = toSearch.compareTo(node.data);
        } else {
            compareResult = comparator.compare(toSearch, node.data);
        }
        if (compareResult > 0) {
            return searchHelp(node.right, toSearch, compareResult); //????
        }
        if (compareResult < 0) {
            return searchHelp(node.left, toSearch, compareResult);
        }
        return node.data;
    }
    /**
     * wrapper method of the insertHelp.
     * No duplicates allowed.
     * @param toInsert a value (object) to insert into the tree.
     */
    @Override
    public void insert(T toInsert) {
        Node<T> newNode = new Node<T>(toInsert);
        if (root == null) {
            root = newNode;
            numOfNodes++;
            return;
        }
        int compareResult = 0;
        insertHelp(root, newNode, compareResult);
    }
    /**
     * Inserts a value (object) to the tree.
     * base cases: 1. when there's a duplicate, return
     *             2. find the left place, insert the new node, return
     *             3. find the right place, insert the new node, return
     * @param current the current node
     * @param newNode the new node to be inserted
     * @param compareResult the result of the comparing
     */
    private void insertHelp(Node<T> current, Node<T> newNode, int compareResult) {
        if (comparator == null) {
            compareResult = newNode.data.compareTo(current.data);
            if (compareResult == 0) {
                return;
            }
        } else {
            compareResult = comparator.compare(newNode.data, current.data);
            if (compareResult == 0) {
                return; //find the duplicate
            }
        }
        if (compareResult > 0) {
            if (current.right == null) {
                current.right = newNode;
                numOfNodes++;
                return;
            }
            insertHelp(current.right, newNode, compareResult);
        } else {
            if (current.left == null) {
                current.left = newNode;
                numOfNodes++;
                return;
            }
            insertHelp(current.left, newNode, compareResult);
        }
    }
    /**
     * iterator implementation.
     * @return iterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator();
    }
    /**
     * inner class for BST that implements Iterator interfaces.
     * @author Siyu Tang
     */
    private class BSTIterator implements Iterator<T> {
        /**
         * the current node.
         */
        private Node<T> curNode;
        /**
         * the stack to store the left children.
         */
        private Stack<Node<T>> stack = new Stack<Node<T>>();
        /**
         * the constructor.
         */
        BSTIterator() {
            curNode = root;
        }
        /**
         * boolean value of whether there's a next node.
         */
        @Override
        public boolean hasNext() {
            return (curNode != null) || (!stack.isEmpty());
        }
        /**
         * next value in the BST.
         * @return the next value in order
         */
        @Override
        public T next() {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            T tmp = curNode.data;
            curNode = curNode.right;
            return tmp;
        }
    }
    /**
     * inner class of Node.
     * @author Siyu Tang
     * @param <T> any type
     */
    private static class Node<T> {
        /**
         * the data of the node.
         */
        private T data;
        /**
         * left child of the current node.
         */
        private Node<T> left;
        /**
         * right child of the current node.
         */
        private Node<T> right;
        /**
         * constructor of the node.
         * @param d data of the node
         */
        Node(T d) {
            this(d, null, null);
        }
        /**
         * another constructor of the node.
         * @param d data of the node
         * @param l left child of the current node
         * @param r right child of the current node
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
        /**
         * toString method of the node.
         * @return the string of data
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }

}
