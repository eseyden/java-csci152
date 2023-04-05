package com.ericseyden.csci_152.ObjectsNTrees;

import java.util.Arrays;

public class Lecture {
    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.insert("Something");
        myList.insert("For");
        myList.insert("Nothing");
        String out = Arrays.toString(myList.All());
        System.out.println(out);
    }
}

class LinkedList {
    Link First;
    int Count;
    LinkedList(){
        First = null;
        Count = 0;
    }

    void insert(Object myValue) {
        Link myLink = new Link(myValue);
        if(First == null) {
            First = myLink;
            Count++;
            return;
        }
        Link Walker = First;
        while(Walker.Next != null){
            Walker = Walker.Next;
        }
        Count++;
        Walker.Next = myLink;
    }

    Object[] All() {
        if(Count == 0) return null;
        Object[] out = new Object[Count];
        Link Walker = First;
        for(int c=0; c<Count; c++){
            out[c] = Walker.Value;
            Walker = Walker.Next;
        }
        return out;
    }
}

class Link {
    Object Value;
    Link Next;
    Link(Object myValue) {
        Value = myValue;
        Next = null;
    }
}
