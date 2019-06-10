package com.shoo.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //shellSort(arr);
        shellSort2(arr);
    }


    // 编写希尔排序
    public static void shellSort(int[] arr) {
        int temp ;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    //
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序" + (++count) + "轮后=" + Arrays.toString(arr));
        }
    }

    // 交换式希尔排序 进行优化 --> 移位
    public static void shellSort2(int[] arr) {
        //  int[] arr = {8, 9, 1, 7, 2,   3, 5, 4, 6, 0};
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;  // 5 6 7 8 9
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
            System.out.println("希尔排序" + (++count) + "轮后=" + Arrays.toString(arr));
        }
    }
}