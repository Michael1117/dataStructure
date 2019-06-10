package com.shoo.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        // 测试图
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;

        // 邻接矩阵的关系  使用二维数组
        int[][] weight = new int[][]{       // 10000(也可以更大)表示不联通
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        // 创建MGraph对象
        MGraph graph = new MGraph(vertexs);

        // 创建一个MinTree
        MinTree minTree = new MinTree();

        minTree.createGraph(graph, vertexs, data, weight);

        minTree.showGraph(graph);

        minTree.prim(graph, 0);
    }
}

// 创建最小生成树  -> 村庄的图
class MinTree {
    // 创建图的邻接矩阵

    /**
     * @param graph  图对象
     * @param vertex 顶点个数
     * @param data   顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertex, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    // 显示图的 邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    // prim 算法，得到最小生成树

    /**
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成 'A' -> 0 'B'-> 1
     */
    public void prim(MGraph graph, int v) { //
        // visited[]  默认元素为0 ，表示没有访问过
        int visited[] = new int[graph.vertexs];

        /*for (int i = 0; i < graph.vertexs; i++) {

        }*/
        // 当前这个结点标记为已访问
        visited[v] = 1;

        // h1 和 h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;  // 将minWeight 初始成一个大数，后面遍历过程中，会被替换

        for (int k = 1; k < graph.vertexs; k++) {   //  有 graph.vertexs个顶点， prim结束后，有graph.vertexs-1条边

            for (int i = 0; i < graph.vertexs; i++) {   // 遍历访问过的结点
                for (int j = 0; j < graph.vertexs; j++) {   // 遍历没有访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        // 替换minWeight
                        //System.out.println(j);
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                        //System.out.println("i = " + i);
                    }
                }

            }

            // 找到最小
            System.out.println("边<" + graph.data[h1] + ", " + graph.data[h2] + "> 权值：" + minWeight);
            // 将当前这个结点标记为已经访问
            //System.out.println(h2);
            System.out.println(h1);
            visited[h2] = 1;
            // minWeight 重新设置为最大值 10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int vertexs; // 图的节点个数
    char[] data; // 存放结点的数据
    int[][] weight; // 存放边，就是邻接矩阵

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}