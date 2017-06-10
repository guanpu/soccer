/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author pguan
 */
public class Temp {
    @SuppressWarnings("empty-statement")
    static int [] quicksort(int[] ar) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int pivot = ar[0];
        for(int i: ar) {
            if(i>ar[0]) {
                right.add(i);
            }
            if(i<ar[0]) {
                left.add(i);
            }
        }
        int[] left_ar;
        int[] right_ar;
        if(left.size()>1){
            left_ar = quicksort(left.stream().mapToInt(i->i).toArray());
        }else{
            left_ar = left.stream().mapToInt(i -> i).toArray();
        }
        if(right.size()>1){
            right_ar = quicksort(right.stream().mapToInt(i->i).toArray());
        }else {
            right_ar = right.stream().mapToInt(i -> i).toArray();
        }
        int[] newarr = new int[ar.length];
        for(int i =0;i<newarr.length;i++){
            if(i<left_ar.length) {
                newarr[i] = left_ar[i];
            }
            if(i==left_ar.length) {
                newarr[i] = pivot;
            }
            if(i>left_ar.length){
                newarr[i] = right_ar[i-left_ar.length-1];
            }
        }
        printArray(newarr);
        return newarr;
    }

    static void printArray(int[] ar) {
        for (int n : ar) {
            System.out.print(n + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
//        List<Integer> left = new ArrayList<>();
//        int[] k = left.stream().mapToInt(i->i).toArray();
//        System.out.println(k.length);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
        }
        quicksort(ar);
    }
    
}
