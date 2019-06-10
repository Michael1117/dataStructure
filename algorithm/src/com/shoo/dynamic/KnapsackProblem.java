package com.shoo.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};  // 物品的重量
        int[] val = {1500, 3000, 2000};    // 物品的价值
        int m = 4; // 背包容量
        int n = val.length;     // 物品的个数

        // 创建二维数组，
        // v[i][j]  表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;        // 将第1列设置为0

        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;        // 将第1行设置为0
        }

        // 根据公式动态规划处理
        for (int i = 1; i < v.length; i++) {      // 不处理第1行
            for (int j = 1; j < v[0].length; j++) { // 不处理第1列
                // 公式
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {

                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];

                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        // 输出一下v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        // 输出最后放入的哪些商品

    }
}
