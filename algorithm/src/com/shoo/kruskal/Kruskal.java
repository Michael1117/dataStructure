package com.shoo.kruskal;

import java.util.Arrays;

public class Kruskal {
    private int edgeNum; // 记录边的个数
    private char[] vertexs; // 顶点数组
    private int[][] matrix; // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;   // 使用INF来表示两个顶点不能联通

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.print();

        /*EData[] edges = kruskal.getEdges();

        System.out.println("排序前" + Arrays.toString(edges));    // 未排序

        kruskal.sortEdges(edges);
        System.out.println("排序后" + Arrays.toString(edges));*/

        kruskal.kruskal();
    }

    // 构造器
    public Kruskal(char[] vertexs, int[][] matrix) {
        // 初始化 顶点数 和 边的个数
        int vlen = vertexs.length;

        // 初始化  顶点， 复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        // 初始化边  复制拷贝的方式
        this.matrix = new int[vlen][vlen];

        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {     // 有效
                    edgeNum++;
                }
            }
        }

    }

    public void kruskal() {
        int index = 0;  // 最后结果数组的索引
        int[] ends = new int[edgeNum];

        EData[] rets = new EData[edgeNum];
        // 获取图中  所有的边的集合 12 条
        EData[] edges = getEdges();
        //System.out.println(("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length));
        // 按照边的权值大小排序 (小-> 大)

        sortEdges(edges);
        // 遍历edges 数组，将边添加到最小生成树中，判断准备加入的边形成了回路，没有构成回路，加入rets，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            // 获取第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            // 获取p1 这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);

            // 获取p2 这个顶点在已有的最小生成树中的终点
            int n  = getEnd(ends, p2);

            // 是否构成回路
            if (m != n) {   // 没有构成回路
                ends[m] = n;    // 设置m 在已有最小生成树的终点
                rets[index++] = edges[i]; // 有一条边加入到rets数组
            }
        }

        // 统计并打印 "最小生成树"，输出 rets
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    // 打印 邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为： \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }

            System.out.println();
        }
    }

    /**
     * 边排序 处理，冒泡排序
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {    // 交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值 如： 'A','B'
     * @return 返回ch 顶点对应的下标，找不到 ，返回-1
     */

    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) { // 找到
                return i;
            }
        }
        // 找不到，返回 -1
        return -1;
    }

    /**
     * 获取图中的边，放到EData[] 数组中，后面要遍历数组
     * 通过matrix 邻接矩阵来获取
     * EData[]  形式 [['A','B',12],['B','F',7]]
     *
     * @return
     */

    // 统计边的条数
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }

        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
     * @param ends  记录了各个顶点对应的终点是哪个，ends是遍历过程中，逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return  下标i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }
}

// 创建一个类EData，它的对象实例就表示一条边
class EData {
    char start; // 边的一个点
    char end;   // 边的另一个点
    int weight; // 权值

    // 构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //


    @Override
    public String toString() {
        return "EData{" +
                "<start=" + start +
                ", end=" + end +
                ">, weight=" + weight +
                '}';
    }
}