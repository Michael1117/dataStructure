package com.shoo2.test;

public class TestRecursive {
    public static void main(String[] args) {
        print(3);
    }

    public static void print(int i) {
        if(i > 0) {
            System.out.println(i);
            print(i-1);
        }
    }
}
