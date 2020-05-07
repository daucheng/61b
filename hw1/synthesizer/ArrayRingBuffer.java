package synthesizer;


import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     *helper function to move first and last
     */
    private int countHelper(int index){
        if (index == capacity - 1){
            return 0;
        }else{
            return index + 1;
        }
    }
    @Override
    public void enqueue(T x){
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last = countHelper(last);
        fillCount = fillCount + 1;
    }//end of enqueue
    @Override
    public T dequeue(){
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T x = rb[first];
        rb[first] = null;
        first = countHelper(first);
        fillCount = fillCount - 1;
        return x;
    }//end of dequeue
    @Override
    public T peek(){
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T x = rb[first];
        return x;
    }//end of peek

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }


    private class queueiterate implements Iterator<T> {

        int current;
        int remain;

        queueiterate(){
            current = first;
            remain = fillCount();
        }

        @Override
        public boolean hasNext() {
            return remain >0 ;
        }

        @Override
        public T next() {
            T x = rb[current];
            remain = remain - 1;
            current = countHelper(current);
            return x;
        }
        @Override
        public void remove() {
            rb[current] = null;
            current = countHelper(current);
        }
    }//end of private Iterator


    @Override
    public Iterator<T> iterator(){
        return new queueiterate();
    }
}//end of class

