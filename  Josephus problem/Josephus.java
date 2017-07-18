import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 08722 Data Structures for Application Programmers.
 * Homework Assignment 2
 * Solve Josephus problem with different data structures
 * and different algorithms and compare running times
 * I would use playWithAD if the rotation is small
 * and use playWithLLAt if the rotation is big with the following reason:
 * 1. With the same size and rotation, playWithAD is always faster than playWithLL
 * because LinkList will create new object every time we add() and does the
 * garbage collection when we delete, which will cost a lot more time than playWithAD.
 * 2. When it comes to playWithAD and playWithLLAt, the former is faster than the
 * latter if the rotation number is small because it is O(N) for playWithAD and O(1) for
 * playWithLLAt.Therefore, if there's a large number of rotation, I would choose playWithLLAt.
 * Andrew ID: siyut
 * @author Siyu Tang
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        int newRotation = rotation;
        if (size < 1 || rotation < 1) {
            throw new RuntimeException();
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 1; i <= size; i++) {
            q.add(i);
        }
        while (q.size() != 1) {
            if (rotation > q.size()) {
                newRotation = rotation % q.size();
                if (newRotation == 0) {
                    newRotation = q.size();
                }
            }
            for (int j = 0; j < newRotation - 1; j++) {
                q.add(q.poll());
            }
            q.poll();
        }
        return q.peek();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        int newRotation = rotation;
        if (size < 1 || rotation < 1) {
            throw new RuntimeException();
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= size; i++) {
            q.add(i);
        }
        while (q.size() != 1) {
            if (rotation > q.size()) {
                newRotation = rotation % q.size();
                if (newRotation == 0) {
                    newRotation = q.size();
                }
            }
            for (int j = 0; j < newRotation - 1; j++) {
                q.add(q.poll());
            }
            q.poll();
        }
        return q.peek();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        int index = rotation - 1;
        if (size < 1 || rotation < 1) {
            throw new RuntimeException();
        }
        List<Integer> l = new LinkedList<Integer>();
        for (int i = 1; i <= size; i++) {
            l.add(i);
        }
        while (l.size() > 1) {
            if (index >= l.size()) {
                index = index % l.size();
            }
            l.remove(index);
            index = index + rotation - 1;
        }
        return l.get(0);
    }
}
