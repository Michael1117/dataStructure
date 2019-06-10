package com.shoo.java;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestOpArray {
    public static void main(String[] args) {
        // 解决数组长度不可变的问题
        int[] arr = new int[]{9,8,7};

        System.out.println(Arrays.toString(arr));

        int dst = 6;

        int[] newArr = new int[arr.length+1];

        for (int i = 0; i < arr.length; i++) {

            newArr[i] = arr[i];
        }

        newArr[arr.length] = dst;

        // 新数组赋值给老数组

        arr = newArr;

        System.out.println(Arrays.toString(arr));
    }
}
