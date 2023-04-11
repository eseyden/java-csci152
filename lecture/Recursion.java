import java.lang.reflect.Field;

public class Recursion {
    public static void main(String[] args) {
        WordTreeNode Root = new WordTreeNode();

        Root.InsertWord("apple");

    }
}

class WordTreeNode {
    boolean IsWord;
    WordTreeNode[] Children;
    WordTreeNode A;
    WordTreeNode B;
    WordTreeNode C;
    WordTreeNode D;
    WordTreeNode E;
    WordTreeNode F;
    WordTreeNode G;
    WordTreeNode H;
    WordTreeNode I;
    WordTreeNode J;
    WordTreeNode K;
    WordTreeNode L;
    WordTreeNode M;
    WordTreeNode N;
    WordTreeNode O;
    WordTreeNode P;
    WordTreeNode Q;
    WordTreeNode R;
    WordTreeNode S;
    WordTreeNode T;
    WordTreeNode U;
    WordTreeNode V;
    WordTreeNode W;
    WordTreeNode X;
    WordTreeNode Y;
    WordTreeNode Z;

    WordTreeNode(){
        Children = new WordTreeNode[]{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
    }

    void InsertWord(String myWord) {
        if(myWord.length() == 0) return;
        myWord = myWord.toUpperCase();
        char Letter = myWord.charAt(0);
        int LetterIndex = (int) Letter - 65;
        myWord = myWord.substring(1);
        if(Children[LetterIndex] == null) {

        }
        System.out.println(LetterIndex);

    }
}
