package com.ericseyden.csci_152.chapter1.section2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Chapter 1 Section 2 Programs");
        System.out.println("----------------------------\n");

        System.out.println("java Quadratic -3.0 2.0");
        Quadratic.main(new String[]{"-3", "2"});

        System.out.println("\njava LeapYear 2004");
        LeapYear.main(new String[]{"2004"});
        System.out.println("\njava LeapYear 1900");
        LeapYear.main(new String[]{"1900"});
        System.out.println("\njava LeapYear 2000");
        LeapYear.main(new String[]{"2000"});

        System.out.println("\njava RandomInt 1000");
//        RandomInt.main({"1000"});
    }
}
