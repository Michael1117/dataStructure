package com.shoo2.test;

public class TestHanoi {
    public static void main(String[] args) {
        hanoi(1, 'A' , 'B', 'C');
    }

    /**
     *
     * @param n  共有n个盘
     * @param from   开始的柱子
     * @param in     中间的柱子
     * @param to     目标柱子
     */
    public static void hanoi(int n, char from , char in, char to) {
        if (n == 1) {
            System.out.println("第一个盘子从" + from + "移动到" + to);
        }else {

        }
    }
}
