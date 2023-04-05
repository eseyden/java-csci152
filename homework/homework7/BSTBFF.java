public class BSTBFF {
    public static void main(String[] args) {
        Tree myTree = new Tree();
        myTree.Insert(100);
        myTree.Insert(10);
        myTree.Insert(1000);
        myTree.Insert(5);
        System.out.println(myTree.Find(100));
    }

}
class Tree {
    Leaf Root;

    void Insert(Comparable Value) {
        if(Root == null) {
            Root = new Leaf(Value);
            return;
        }
        boolean Searching = true;
        Leaf CurrentLeaf = Root;
        while(Searching) {
            int comparison = CurrentLeaf.Value.compareTo(Value);
            if(comparison > 0){
                if(CurrentLeaf.Left == null) {
                    CurrentLeaf.Left = new Leaf(Value);
                    Searching = false;
                } else {
                    CurrentLeaf = CurrentLeaf.Left;
                }
            } else if (comparison < 0) {
                if(CurrentLeaf.Right == null) {
                    CurrentLeaf.Right = new Leaf(Value);
                    Searching = false;
                } else {
                    CurrentLeaf = CurrentLeaf.Right;
                }
            } else Searching = false;
        }
    }

    Object Find(Comparable Value) {
        boolean Searching = true;
        Leaf CurrentLeaf = Root;
        while(Searching) {
            int comparison = CurrentLeaf.Value.compareTo(Value);
            if(comparison < 0){
                if(CurrentLeaf.Left == null) {
                    CurrentLeaf = null;
                    Searching = false;
                } else {
                    CurrentLeaf = CurrentLeaf.Left;
                }
            } else if (comparison > 0) {
                if(CurrentLeaf.Right == null) {
                    CurrentLeaf = null;
                    Searching = false;
                } else {
                    CurrentLeaf = CurrentLeaf.Right;
                }
            } else {
                Searching = false;
            }
        }
        return CurrentLeaf;
    }
}

class Leaf {
    Leaf Left;
    Leaf Right;
    Comparable Value;
    Leaf(Comparable myValue) {
        Value = myValue;
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "Left=" + Left +
                ", Right=" + Right +
                ", Value=" + Value +
                '}';
    }
}

