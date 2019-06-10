package com.shoo.recursion;

public class Queue8 {

    // 定义两个max表示共有多少个皇后
    int max = 8;

    // 定义array, 保存皇后放置位置的结果，如arr = {0,4,7,5,2.6,1,3}

    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        // 测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法" , count);
    }


    // 编写一个方法，放置第n个皇后
    public void check(int n) {
        if (n == max) {  // n = 8
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前皇后 n，放到该行的第1列
            array[n] = i;
            // 判断当前放置第n个
            if (judge(n)) {  // 不冲突

                check(n + 1);
            }
        }
    }

    // 查看当我们放置第n个皇后，检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第n皇后  n 从0开始
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 写一个方法， 可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}
