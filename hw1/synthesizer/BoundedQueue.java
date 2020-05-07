package synthesizer;
import java.util.Iterator;
public interface BoundedQueue<T> extends Iterator<T> {

    /**
     *return the size of buffer
     */
    int capacity();
    /**
     *return number of items currently in the buffer
     */
    int fillCount();
    /**
     * add item x to the end
     */
    void enqueue(T x);
    /**
     * @return delete and return item from the front
     */
    T dequeue();
    /**
     * @return (but do not delete)item from the front
     */
    T peek();

    /**
     * is the buffer empty(fillcount equals zero)
     */
    default boolean isEmpty(){
        if (fillCount() == 0){return true;}
        return false;
    }//end of isEmpty
    /**
     * is the buffer full(fillCount is same as capacitiy)
     */
    default boolean isFull(){
        if (fillCount() == capacity()){return true;}
        return false;
    }//end of isFull

    Iterator<T> iterator();
}//end of interface
