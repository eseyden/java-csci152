import java.util.Arrays;

public class StackMath {
    public static void main(String[] args) {
        MathStack myStack = new MathStack();
        for(int c=0; c<args.length; c++){
            if(args[c].equals(")")) {
                String Next = (String) myStack.Pop();
                while(!Next.equals("(")) {
                    int Number2 = Integer.parseInt((String) myStack.Pop());
                    String op = (String) myStack.Pop();
                    int Number1 = Integer.parseInt((String) myStack.Pop());
                    Next = (String) myStack.Pop();
                    switch (op) {
                        case "+" -> myStack.Push(Number1 + Number2);
                        case "-" -> myStack.Push(Number1 - Number2);
                        case "*" -> myStack.Push(Number1 * Number2);
                        case "/" -> myStack.Push(Number1 / Number2);
                    }
                }
            } else {
                myStack.Push(args[c]);
            }
        }
        System.out.println(Arrays.toString(myStack.All()));
    }

}

class MathStack {

    MathStackItem Top;

    void Push(Object item) {
        Top = new MathStackItem(item, Top);
    }

    Object Pop() {
        if(Top == null) return null;
        MathStackItem out = Top;
        Top = out.next;
        return out.value;
    }

    Object[] All() {
        Object[] out;
        int count = 0;
        MathStackItem current = Top;
        while(current != null) {
            count++;
            current = current.next;
        }
        out = new Object[count];
        current = Top;
        while(current != null) {
            out[count - 1] = current;
            count--;
            current = current.next;
        }
        return out;
    }
}

class MathStackItem {
    Object value;
    MathStackItem next;
    MathStackItem(Object item,MathStackItem nextItem) {
        value = item;
        next = nextItem;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}