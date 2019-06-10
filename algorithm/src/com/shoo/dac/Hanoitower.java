package com.shoo.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    // 汉诺塔 的移动方法
    // 使用分治算法

    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "-> " + c);
        } else {
            //
            hanoiTower(num - 1, a, c, b);

            // 2. 把最下边的盘 A -> C
            System.out.println("第" + num + "个盘从" + a + "-> " + c);

            // 3. 把B塔的所有盘 从 B -> C

            hanoiTower(num - 1, b, a, c);
        }
    }

}
