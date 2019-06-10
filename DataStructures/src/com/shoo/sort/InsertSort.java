package com.shoo.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};

        insertSort(arr);
    }

    // 插入排序
    public static void insertSort(int[] arr) {
        // 使用逐步推导的方式来讲解，便于理解
        // 1轮  {101, 34, 119, 1} => {34, 101, 119,1}

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            // 当退出while循环时， 说明插入的位置找到， insertIndex + 1

            arr[insertIndex + 1] = insertVal;

            System.out.println("第" + i + "轮插入");
            System.out.println(Arrays.toString(arr));
        }

        /*// 第2轮
        insertVal = arr[2];
        insertIndex = 2 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }

        // 当退出while循环时， 说明插入的位置找到， insertIndex + 1

        arr[insertIndex + 1] = insertVal;

        System.out.println("第2轮插入");
        System.out.println(Arrays.toString(arr));

        // 第3轮
        insertVal = arr[3];
        insertIndex = 3 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }

        // 当退出while循环时， 说明插入的位置找到， insertIndex + 1

        arr[insertIndex + 1] = insertVal;

        System.out.println("第3轮插入");
        System.out.println(Arrays.toString(arr));*/
    }
}
