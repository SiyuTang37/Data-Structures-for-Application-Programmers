import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        BST<Word> tree1 = new BST<Word>();
        Word w0 = new Word("0");
        Word w1 = new Word("0");
        Word w2 = new Word("1");
        Word w3 = new Word("1");
        Word w4 = new Word("2");
        Word w5 = new Word("2");
        Word w6 = new Word("3");
        Word w7 = new Word("3");
        Word w8 = new Word("4");
        Word w9 = new Word("4");
        Word w10 = new Word("5");
        Word w11 = new Word("5");
        tree1.insert(w0);
        tree1.insert(w1);
        tree1.insert(w2);
        tree1.insert(w3);
        tree1.insert(w4);
        tree1.insert(w5);
        tree1.insert(w6);
        tree1.insert(w7);
        tree1.insert(w8);
        tree1.insert(w9);
        tree1.insert(w10);
        tree1.insert(w11);
        Iterator<Word> itr = tree1.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println(tree1.getNumberOfNodes());
    }

}
