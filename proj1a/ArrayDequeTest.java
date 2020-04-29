import org.junit.Test;
import static org.junit.Assert.*;

/** Tests ArrayDeque class */
public class ArrayDequeTest {
    /**
     *addFirst & addLast test
     */
    @Test
    public void addTest(){
        // [][][][][][]
        ArrayDeque<Integer> T1 = new ArrayDeque<>();
        T1.addFirst(2);
        // [][][2][][][]
        assertEquals(Integer.valueOf(2), T1.get(2));
        T1.addFirst(1);
        assertEquals(Integer.valueOf(1), T1.get(1));
        T1.addLast(3);
        assertEquals(Integer.valueOf(3), T1.get(3));
        T1.addLast(4);
        assertEquals(Integer.valueOf(4), T1.get(4));
    }

    @Test
    public void removeTest(){
        ArrayDeque<Integer> T1 = new ArrayDeque<>();
        T1.addFirst(2);
        T1.addFirst(1);
        T1.addLast(3);
        T1.addLast(4);
        // [][1][2][3][4][]
        T1.removeFirst();
        T1.removeLast();
        // [][5][2][3][6][]
        T1.addFirst(5);
        assertEquals(Integer.valueOf(5), T1.get(1));
        T1.addLast(6);
        assertEquals(Integer.valueOf(6), T1.get(4));
    }
    @Test
    public void resizeTest(){
        ArrayDeque<Integer> T1 = new ArrayDeque<>();
        assertEquals(6, T1.getLength());
        T1.addFirst(2);
        T1.addFirst(1);
        T1.addLast(3);
        assertEquals(6,T1.getLength());
        T1.addLast(4);
        assertEquals(4,T1.size());
        assertEquals(12,T1.getLength());
        T1.addFirst(0);
        T1.addLast(5);
        assertEquals(Integer.valueOf(0),T1.get(4));
        assertEquals(Integer.valueOf(4),T1.get(8));

    }
}