package com.shoo.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];

        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(arr));
    }


    // 分 + 合方法

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;  // 中间索引
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);

            // 到合并
            merge(arr, left, mid, right, temp);
        }
    }
    // 合并的方法

    /**
     * @param arr   原始数组
     * @param left  左边有序的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;  // 右边有序序列的初始索引
        int t = 0;   // 指向temp 数组的当前索引

        //  先把左右两边 的数据 按照规则填充到 temp 数组
        //  直到左右两边的有序序列，有一边处理完毕为止

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {  //
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        // (二)
        while (i <= mid) {  // 左边的有序序列还有剩余元素，全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft = " + tempLeft + " right =" + right);

        while (tempLeft <= right) {  // 0,1 2,3 0,3

            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
