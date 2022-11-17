package dataStructures;

/**
 * Using a circular array
 */
public class Queue <E> {

    // index of the head
    private int head;
    private int tail;  // tail = (head + size - 1) mod length

    private int size;

    // circular array
    private E[] array;

    public Queue() {
        // initialize array so it has 10 slots
        array = (E[]) new Object[10];
    }

    /**
     * Places an element at the front of the queue. Element can be any generic type E
     * @param element
     */
    public void enqueue(E element) {
        // Check if we need to increase size of array
        if (size == array.length) {
            E[] temp = (E[]) new Object[array.length*2];
            // copying elements from old array to new array
            // for loop is supposed to be circular (i.e. goes back to start)
            int i;
               for (i = 0; i < array.length; i++) {
                   temp[i] = array[mod(head + i, array.length)];
               }
            head = 0;
            array = temp;
        }

        array[mod(getTail()+1, array.length)] = element;
        size++;
    }

    /**
     * Removes the element at the front of the queue. Returns that element
     * @return
     */
    public E dequeue() {
        if (size <= 0) {
            throw new IllegalArgumentException("Called dequeue on empty queue");
        }
        E element = array[head];
        size--;
        head = mod((head + 1), array.length);
        return element;
    }

    public String toString() {
        String result = "[";
        for (E element : array) {
            if (element != null) {
                result += (element.toString() + " ");
            } else {
                result += "null ";
            }
        }
        result = result.trim(); // remove white space at end
        return result + "]";
    }

    /**
     * Prints out the Queue, from the head to the tail. The difference between this and the toString() method
     * is that this will always start at the head, and will not print any null values.
     */
    public void printQueue() {
        int i;
        System.out.print("[");
        for (i = head; (mod(i, array.length) < getTail()); i++) {
            System.out.print(array[i] + " ");
        }
        // Print out last element
        System.out.print(array[i] + "]\n");

    }

    private int getTail() {
        return mod(head+size-1, array.length);
    }

    /**
     * Mod function that works for negative integers
     * @param num
     * @param m
     * @return
     */
    private int mod(int num, int m) {
        if (m < 0) {
            throw new IllegalArgumentException("Negative mod called for mod() method");
        }

        if (num < 0) {
            num = m - ( (-1*num )% m);
        }
        return num%m;
    }


}
