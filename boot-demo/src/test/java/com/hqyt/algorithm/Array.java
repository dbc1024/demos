package com.hqyt.algorithm;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: CSZ
 * @date: 2018/11/26 20:27
 */
public class Array {

    @Test
    public void reverseArray() {

        int[] intArr = new int[]{1, 2, 3, 4, 5};

        int[] reverseArr = new int[intArr.length];
        for(int i=intArr.length-1; i>=0; i-- ){

            reverseArr[intArr.length-1-i]=intArr[i];
        }

        for (int i : reverseArr) {
            System.out.println(i);
        }

    }


    @Test
    public void distinct(){

        int[] intArr = new int[]{1, 2, 3, 3, 4, 4, 5};

        Set<Integer> set = new HashSet<>();

        for (int num : intArr) {
            set.add(num);
        }

        int[] distinctArr = new int[set.size()];
        int i=0;
        for (Integer num : set) {
            distinctArr[i]= num;
            i++;
        }

        for (int i1 : distinctArr) {
            System.out.println(i1);
        }

    }
}
