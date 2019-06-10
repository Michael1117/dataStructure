package com.shoo2.test;

public class TestFibonacci {
    public static void main(String[] args) {
        // 1 1 2 3 5 8 13
        System.out.println(fibonacci(8));
    }

    public static int fibonacci(int i) {
        if (i == 1 || i == 2) {
            return 1;
        }else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }

    }
}
