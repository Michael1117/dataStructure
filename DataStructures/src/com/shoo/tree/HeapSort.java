package com.shoo.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, -1, 89, 34};
        heapSort(arr);
    }

    // 编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        int temp = 0;

        System.out.println("堆排序");

        // 分步完成
        /*adjustHeap(arr, 1, arr.length);
        System.out.println("第一次" + Arrays.toString(arr));
        adjustHeap(arr, 0 , arr.length);

        System.out.println("第二次" + Arrays.toString(arr));*/

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println("数组" + Arrays.toString(arr));
    }

    // 将一个数组(二叉树)，调整成一个大顶堆

    /**
     * @param arr    待调整的数组
     * @param i      非叶子结点在数组中的索引
     * @param length 多少个结点进行调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];   // 先取出当前元素的值，保存在临时变量中

        // k = i * 2 + 1  k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {  // 左子结点 < 右子结点
                k++;  // k指向右子结点
            }

            if (arr[k] > temp) {    // 如果子结点 > 父结点
                arr[i] = arr[k];    // 把较大的值赋给当前结点
                i = k;          // i指向k，继续循环比较
            } else {
                break;
            }
        }

        // 当for  循环结束后，已经将以i 为父结点的树的最大值，放在了 最顶 (局部)
        arr[i] = temp;      // 将temp的值放到调整后的位置


    }
}
