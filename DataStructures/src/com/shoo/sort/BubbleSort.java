package com.shoo.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {23, 9, -1, 10, -2, 3, 1};
        System.out.println("排序前");

        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("排序后");

        System.out.println(Arrays.toString(arr));

    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            //System.out.println("第" + (i + 1) + "趟排序后的数组");
            //System.out.println(Arrays.toString(arr));

            if (!flag) {  // 没有交换过
                break;
            } else {
                flag = true;
            }
        }

    }
}
