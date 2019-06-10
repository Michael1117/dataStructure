package com.shoo.recursion;

public class MiGong {
    public static void main(String[] args) {
        // 创建一个二维数组模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 1: 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板  1表示
        map[3][1] = 1;
        map[3][2] = 1;
        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }

            System.out.println();
        }

        // 使用递归回溯找路
        //setWay(map, 1, 1);
        setWay2(map, 1, 1);
        // 输出新的地图， 小球走过，并标识过的地图

        System.out.println("小球走过，并标识过的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }

            System.out.println();
        }
    }

    // 使用递归回溯来给小球找路

    /**
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {  // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {  // 如果当前这个点还没有走过
                // 下-> 右 -> 上 -> 左
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {  // 右
                    return true;
                } else if (setWay(map, i - 1, j)) {  // 上
                    return true;
                } else if (setWay(map, i, j - 1)) {  // 左
                    return true;
                } else {
                    // 该点走不通， 是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {  // map[i][j] != 0, 可能是1,2,3
                return false;
            }
        }
    }

    // 修改策略  上 -> 右 -> 下 -> 左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {  // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {  // 如果当前这个点还没有走过
                // 下-> 右 -> 上 -> 左
                map[i][j] = 2;
                if (setWay2(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {  // 右
                    return true;
                } else if (setWay2(map, i + 1, j)) {  // 下
                    return true;
                } else if (setWay2(map, i, j - 1)) {  // 左
                    return true;
                } else {
                    // 该点走不通， 是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {  // map[i][j] != 0, 可能是1,2,3
                return false;
            }
        }
    }
}
