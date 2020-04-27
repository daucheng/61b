
public class ArrayDeque <T> {
    private T[] items;
    private int size;
    private int capacity = 100; //initial capacity
    private int eFactor = 2; //expasion factor
    private double uratio = 0.25;

    /**Constructor */
    public ArrayDeque(){
        T[] items = (T []) new Object[capacity];
        size = 0;
    }
    
    /**method */
    private void resize(){
        T[] newitems = (T []) new Object[capacity * eFactor];
        System.arraycopy(items, 0, newitems, 0, size);
        items = newitems;
    }
    
    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    
    public void addLast(T item){
        if (size/items.length > uratio){
            resize();
        }
        items[size] = item;
        size += 1; 
    }
    public T getLast(){
        return items[size -1];
    }
    public T removelast(){
        T x = getLast();
        items[size-1] = null;
        size -= 1;
        return x;
    }
    public T get(int i){
        return items[i];
    }
    
}