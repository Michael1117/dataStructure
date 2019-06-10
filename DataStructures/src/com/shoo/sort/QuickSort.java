package com.shoo.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23 ,-567, 70};  // {-9, -567, 0, 23 , 78, 70}

        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr = " + Arrays.toString(arr));
    }                                                   // r           l



    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        // pivot: 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0;  // 临时变量， 作为交换时使用
        // 比pivot小的值放到 左边
        // 比pivot大的值放到 右边
        while (l < r) {
            // 在pivot左边找，找到 >= pivot的值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot右边找，找到 <= pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完后，arr[l] == pivot 值
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果 l == r, l++, r--, 否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
