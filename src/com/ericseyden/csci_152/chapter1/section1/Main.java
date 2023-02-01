package com.ericseyden.csci_152.chapter1.section1;

import com.ericseyden.csci_152.chapter1.section1.HelloWorld;
import com.ericseyden.csci_152.chapter1.section1.UseArgument;

public class Main {
    public static void main(String[] args) {
        System.out.println("Chapter 1 Section 1 Programs");
        System.out.println("----------------------------");
        System.out.println("java HelloWorld");
        HelloWorld.main(new String[]{});
        System.out.println("\njava UseArgument Dave");
        UseArgument.main(new String[]{"Dave"});
    }
}
