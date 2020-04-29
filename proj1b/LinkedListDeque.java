
public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel; // This is circular sentinel !!
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel && sentinel.prev == sentinel && size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void printDeque() {
        Node currentNode = sentinel;
        while (currentNode.next != sentinel) {
            System.out.print(currentNode.next.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }


    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }


    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }


    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }

        T removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }


    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node currentNode = sentinel.next;
        while (index != 0) {
            currentNode = currentNode.next;
            index -= 1;
        }
        return currentNode.item;
    }

    private T getRecursiveHelper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }

        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }
    /**for Test use
    public static void main(String[] args) {
        LinkedListDeque<Integer> q = new LinkedListDeque<>();
        q.addFirst(1);
        q.addLast(-1);
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("size: " + q.size());
        q.printDeque();
     }
     */
}