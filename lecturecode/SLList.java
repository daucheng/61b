public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void printline(){
        IntNode x = first;        
        while (x.next != null){
            System.out.print(x.item);
            x = x.next;
        }
        System.out.print(x.item);
        System.out.println();
    }
    public void insert(int item, int position){
        IntNode x = first;
        if (x == null || position == 0){
            addFirst(item);
            return;
        }
        while (position > 1 && x.next != null){
            position -= 1;
            x = x.next;
        }
        x.next = new IntNode(item,x.next);
    }

    public void reverse(){
        IntNode nxtNode = first; 
        IntNode addedNode = null;
        while (nxtNode != null){
            IntNode remainNode = nxtNode.next;
            nxtNode.next = addedNode;
            addedNode = nxtNode;
            nxtNode = remainNode;           
        }
        first = addedNode;
    }
    public void iterrev(){
        first = iterrevhelper(first);
    }
    private IntNode iterrevhelper(IntNode first){
        if (this.first == null || this.first.next == null){
            return this.first;            
        }
        else{
            IntNode reversed = iterrevhelper(this.first.next);
            this.first.next.next = this.first;
            this.first.next = null;
            return reversed;
        }
    }
    public static void main(String[] args) {
        SLList a = new SLList();
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.printline();
        a.insert(4,3);
        a.printline();
        a.reverse();
        a.printline();
    }
}


