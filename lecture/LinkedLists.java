import java.util.Arrays;
import java.util.StringTokenizer;

public class LinkedLists {
    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList(new Booth("Stuff", 3));
        myLinkedList.add(new Booth("Apple",42));
        myLinkedList.remove("Pear");
        System.out.println(Arrays.toString(myLinkedList.toArray()));

        Stack myStack = new Stack();
        myStack.Push("World");
        myStack.Push(" ");
        myStack.Push("Hello");
        System.out.print(myStack.Pop());
        System.out.print(myStack.Pop());
        System.out.print(myStack.Pop());
        System.out.println();

        Queue myQueue = new Queue();
        myQueue.add("Hello ");
        myQueue.add("World");
        System.out.print(myQueue.poll());
        System.out.print(myQueue.poll());
        System.out.println();
    }
}

class Booth {
    String name;
    int count;

    Booth(String name, int count) {
        this.name = name;
        this.count = count;
    }

    boolean equals(Booth booth) {
        return booth.name.equals(this.name);
    }

    boolean equals(String name) {
        return this.name.equals(name);
    }

    public String toString() {
        return name;
    }
}

class Stack {

    StackItem top;

    void Push(Object item) {
        top = new StackItem(item,top);
    }

    Object Pop() {
        StackItem out = top;
        top = out.next;
        return out.value;
    }
}

class StackItem {
    Object value;
    StackItem next;
    StackItem(Object item,StackItem nextItem) {
        value = item;
        next = nextItem;
    }
}

class Queue {
    QueueItem Front;
    QueueItem Back;

    void add(Object value) {
        if(Front == null) {
            Front = new QueueItem(value);
            Back = Front;
        } else {
            Back.next = new QueueItem(value);
            Back = Back.next;
        }
    }

    Object poll() {
        if(Front == null) {
            return null;
        }
        QueueItem out = Front;
        Front = Front.next;
        return out.value;
    }

}
class QueueItem {
    Object value;
    QueueItem next;

    QueueItem(Object value) {
        this.value = value;
    }

}

class LinkedList {
    Object item;
    LinkedList next;

    LinkedList(Object item) {
        this.item = item;
    }

    void add(Object item) {
        LinkedList myLinkedList = this;
        while(true) {
            if(myLinkedList.next == null) {
                myLinkedList.next = new LinkedList(item);
                break;
            } else {
                myLinkedList = myLinkedList.next;
            }
        }
    }

    void remove(Object item) {
        LinkedList myLinkedList = this;
        LinkedList prevLinkedList = null;
        while(myLinkedList != null) {
            if(myLinkedList.item.equals(item)) {
                if(myLinkedList == this) {
                    this.item = this.next.item;
                    this.next = this.next.next;
                } else {
                    prevLinkedList.next = myLinkedList.next;
                }
                break;
            }
            prevLinkedList = myLinkedList;
            myLinkedList = myLinkedList.next;
        }
    }

    Object get(int location) {
        LinkedList current = this;
        for(int c=0; c<= location; c++) {
            if(c == location) {
                return current.item;
            }
            if(current.next == null ) {
                return null;
            }
            current = current.next;
        }
        return null;
    }

    int length() {
        int count = 1;
        LinkedList myLinkedList = this;
        while(true) {
            if(myLinkedList.next == null) {
                break;
            } else {
                myLinkedList = myLinkedList.next;
                count++;
            }
        }
        return count;
    }

    Object[] toArray() {
        Object[] out = new Object[this.length()];
        LinkedList myLinkedList = this;
        int c = 0;
        while(true) {
            out[c]= myLinkedList.item.toString();
            myLinkedList = myLinkedList.next;
            if(myLinkedList == null) {
                break;
            }
            c++;
        }
        return out;
    }

    public void insert(Object item, int location ) {

    }
}