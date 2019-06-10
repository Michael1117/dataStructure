package com.shoo.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index = " + fibSearch(arr, 1234));
    }

    // 非递归方法得到
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;
        int mid = 0;

        int f[] = fib();

        while (high > f[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(a, f[k]); // f[k] ==> f[5] ==>  8为长度 把a 复制到temp中 temp={1, 8, 10, 89, 1000, 1234, 0, 0}

        for (int i = high + 1; i < temp.length; i++) {  // temp.length = 8
            temp[i] = a[high];
        }
        // temp={1, 8, 10, 89, 1000, 1234, 1234, 1234}
        while (low <= high) {
            // 1 1 2 3 5 8
            //System.out.println(k);  // 5 3
            mid = low + f[k - 1] - 1; // 4
           // System.out.println(mid);  // 4 6
            System.out.println(temp[mid]);
            if (key < temp[mid]) {  // 1234 < 1000
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                System.out.println(mid);  // 6
                System.out.println(high);  // 5
                if (mid <= high) {

                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }
}


// 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987  1597