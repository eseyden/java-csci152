package com.ericseyden.csci_152.Sorters.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        int[] arr = new int[] {104034, 4,194,23,1,553,27,  5784, 39, 2, 14, 554 ,3, 234, 564, 241, 9999};
        MergeSort myMergeSort = new MergeSort(arr);
        System.out.println(Arrays.toString(myMergeSort.arr));
    }
}

class QuickSort {
    int[] arrayToSort;

    Random myRandom;

    QuickSort(int[] myArray) {
        myRandom = new Random();
        arrayToSort = myArray;
        int low = 0;
        int high = arrayToSort.length - 1;
        partition(low,high);
    }

    void partition(int start, int end) {
        int pivotIndex = myRandom.nextInt(start,end);
        int pivotValue = arrayToSort[pivotIndex];
        int lowIndex = start;
        int highIndex = end;
        for(int c=start; c<=end; c++){
            if(arrayToSort[c] == pivotValue) continue;
            else if(arrayToSort[c] < pivotValue) {
                int tmp = arrayToSort[lowIndex];
                arrayToSort[lowIndex] = c++;
                arrayToSort[c] = tmp;
            }

        }
    }
}

class MergeSort {
    int subSize;
    int[] arr;

    MergeSort(int[] myArray) {
        arr = myArray;
        sort();
    }

    void sort()  {
        if(arr.length < 2 ) return;
        int[][] splits = split(arr);
        MergeSort left = new MergeSort(splits[0]);
        MergeSort right = new MergeSort(splits[1]);
        arr = merge(left.arr,right.arr);
    }


    int[][] split(int[] myArray) {
        int aLength = myArray.length / 2;
        int bLength = myArray.length - aLength;
        int[] aArray = new int[aLength];
        int[] bArray = new int[bLength];
        int[][] out = new int[][] {aArray, bArray};
        for(int c=0; c<aLength; c++) {
            aArray[c] = myArray[c];
        }
        for(int c=0; c<bLength; c++) {
            bArray[c] = myArray[aLength+c];
        }
        return out;
    }

    int[] merge(int[] aArray, int[] bArray) {
        int aIndex = 0;
        int bIndex = 0;
        int[] out = new int[aArray.length + bArray.length];
        int index = 0;
        while(aIndex < aArray.length && bIndex < bArray.length) {
            if(aArray[aIndex] < bArray[bIndex]) {
                out[index++] = aArray[aIndex++];
            } else {
                out[index++] = bArray[bIndex++];
            }
        }
        while(aIndex < aArray.length) {
            out[index++] = aArray[aIndex++];
        }
        while(bIndex < bArray.length) {
            out[index++] = bArray[bIndex++];
        }
        return out;
    }
}
