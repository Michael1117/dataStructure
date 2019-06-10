package com.shoo.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {5678,12457,53, 3, 542, 748, 14, 214};

        radixSort(arr);

    }

    public static void radixSort(int[] arr) {
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 得到最大数是几位数
        int maxLength = (max + "").length();

        // 第1轮(个位)
        //
        int[][] bucket = new int[10][arr.length];

        int[] bucketElementCounts = new int[10];

        for (int l = 0, n = 1; l < maxLength; l++, n *= 10) {
            for (int i = 0; i < arr.length; i++) {

                int digitOfElement = arr[i] / n % 10;  // 个位的值

                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        // 取出元素放入array中
                        arr[index++] = bucket[k][j];

                    }
                }
                bucketElementCounts[k] = 0;
            }


            System.out.println("第" + (l + 1) + "轮，对个位的排序处理 arr = " + Arrays.toString(arr));
        }

        /*for (int i = 0; i < arr.length; i++) {

            int digitOfElement = arr[i] % 10;  // 个位的值

            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement] ++;
        }

        int index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                for (int j = 0; j < bucketElementCounts[k]; j++) {
                    // 取出元素放入array中
                    arr[index++] = bucket[k][j];

                }
            }
            bucketElementCounts[k] = 0;
        }


        System.out.println("第1轮，对个位的排序处理 arr = " + Arrays.toString(arr));


        // ========================================================
        for (int i = 0; i < arr.length; i++) {

            int digitOfElement = arr[i] / 10 % 10;  // 个位的值

            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement] ++;
        }

        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                for (int j = 0; j < bucketElementCounts[k]; j++) {
                    // 取出元素放入array中
                    arr[index++] = bucket[k][j];

                }
            }
            bucketElementCounts[k] = 0;
        }


        System.out.println("第2轮，对个位的排序处理 arr = " + Arrays.toString(arr));


        for (int i = 0; i < arr.length; i++) {

            int digitOfElement = arr[i] / 100 % 10;  // 个位的值

            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            bucketElementCounts[digitOfElement] ++;
        }

        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                for (int j = 0; j < bucketElementCounts[k]; j++) {
                    // 取出元素放入array中
                    arr[index++] = bucket[k][j];

                }
            }
        }


        System.out.println("第3轮，对个位的排序处理 arr = " + Arrays.toString(arr));*/
    }
}
