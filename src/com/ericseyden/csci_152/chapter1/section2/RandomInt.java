package com.ericseyden.csci_152.chapter1.section2;

public class RandomInt {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double r = Math.random();
        int value = (int) (r * n);
        System.out.println(value);
    }
}
