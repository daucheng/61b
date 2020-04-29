
public class ArrayDeque <T> implements Deque<T> {

    /**
     * Field
     */
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    private int capacity = 6;
    /**
     * Constructor
     */
    public ArrayDeque(){
        items = (T []) new Object[capacity];
        int mid = capacity / 2;
        nextFirst = mid - 1;
        nextLast = mid;
        size = 0;
    }
    
    /**
     * Method
     */
    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        return false;
    }

    private void resize(int newcapacity){
        T[] newitems = (T []) new Object[newcapacity];
        //                 0   1  2  3  4  5
        //                [nF][x][x][x][nL][ ]      old items
        //  0  1  2  3   4  5  6  7   8  9  10 11 12
        // [ ][ ][ ][nF][x][x][x][nL][ ][ ][ ][ ][ ]  newItems
        int first = nextFirst + 1;
        int newFirst = newitems.length/2 - size()/2;
        System.arraycopy(items,first,newitems, newFirst, size);
        items = newitems;
        //new field
        nextFirst = newFirst - 1;
        nextLast = newFirst + size();
    }
    @Override
    public void addFirst(T item){
        if (size() >= (items.length/2)){
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = nextFirst - 1;
    }
    @Override
    public void addLast(T item){
        if (size() >= (items.length/2)){
            resize(items.length * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = nextLast + 1;
    }
    @Override
    public T get(int index){
        return items[index];
    }
    @Override
    public T removeFirst(){
        T x = items[nextFirst +1];
        size -= 1;
        nextFirst = nextFirst +1;
        return x;
    }
    @Override
    public T removeLast(){
        T x = items[nextLast - 1];
        size -= 1;
        nextLast = nextLast - 1;
        return x;
    }
    @Override
    public void printDeque(){
        int x = nextFirst + 1;
        while (x < nextLast){
            System.out.print(items[x]);
            x += 1;
        }
        System.out.println();
    }
    public T getLast(){
        return items[nextLast -1];
    }
    public int getLength(){
        return items.length;
    }
    /**
     * To do:
     * resize
     * addFirst ok
     * addLast ok
     * removeFirst ok
     * removeLast ok
     * get ok
     * getlast ok
     * printDeque
     */

}