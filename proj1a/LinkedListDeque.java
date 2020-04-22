
public class LinkedListDeque<T> {
    
    private class Node{
        public T item;
        public Node next;
        public Node prev;
        
        public Node(T i, Node n, Node p){
            item = i;
            next = n;
            prev = p;
        }

    }
    private Node sentinel;
    private int size;
    
    /**Constructor */
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    
    /**Add an item to the front of deque*/
    public void addFirst(T item){
        size += 1;
        sentinel.next = new Node(item,sentinel,sentinel.next); //將sentinel的next連接到一個新的Node，新node的next接到原本第一個node，新node的prev接回sentinel
        sentinel.next.next.prev = sentinel.next; //原本第一個node的prev(原本指向sentinel)現在連到新創的node
    }
    /**Add an item to the last of deque*/
    public void addLast(T item){
        size += 1;
        sentinel.prev = new Node(item,sentinel.prev,sentinel); //將sentinel的prev連接到一個新的Node，新node的prev接到原本最後一個node，新node的next接回sentinel
        sentinel.prev.prev.next = sentinel.prev; //原本最後一個node的next(原本指向sentinel)現在連到新的last node
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if (sentinel.next == sentinel && sentinel.prev == sentinel && size==0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node a = sentinel;
        while (a.next != a){
            System.out.print(a.next.item);
            a = a.next;
        }
    }


}