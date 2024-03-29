package com.shoo.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;  // 存储顶点的集合
    private int[][] edges;   // 存储图对应的邻接矩阵
    private int numOfEdges;  // 表示边的数目

    // 定义数组boolean[] ,记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 测试 图是否创建成功
        int n = 5;  // 结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);

        // 循环添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边
        // A-B  A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);  // A-B
        graph.insertEdge(0, 2, 1);  // A-C
        graph.insertEdge(1, 2, 1);  // B-C
        graph.insertEdge(1, 3, 1);  // B-D
        graph.insertEdge(1, 4, 1);  // B-E

        // 显示邻接矩阵
        graph.showGraph();

        // 测试dfs 遍历
        //System.out.println("深度遍历");
        //graph.dfs();  // A-B-C-D-E

        System.out.println("广度优先!");

        graph.bfs();
    }

    // 构造器
    public Graph(int n) {
        // 初始化矩阵 vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    // 得到第一个邻接结点的下标 w

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeibour(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    // 根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeibour(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    // 深度优先遍历算法
    // i 第1次为 0
    public void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        // 将结点设置为已经访问过
        isVisited[i] = true;
        // 查找结点v 的第一个邻接结点w
        int w = getFirstNeibour(i);
        while (w != -1) {  // 说明有
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w结点 已经被访问过
            w = getNextNeibour(i, w);
        }
    }

    // 对dfs 进行一个重载 ，遍历所有的结点，并进行dfs
    public void dfs() {
        // 遍历所有的结点 ，进行dfs [回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //  对一个结点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u;      // 表示队列的头结点对应的下标
        int w;      // 邻接结点w
        // 队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        // 访问结点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        // 标记为已访问
        isVisited[i] = true;

        // 将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻结点的下标w
            w = getFirstNeibour(u);
            while (w != -1) {  // 找到
                // 是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }

                // 以u 为前驱点找w 后面的下一个邻接点
                w = getNextNeibour(u, w);   // 体现广度优先
            }
        }

    }

    // 遍历所有结点，都进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
    // 插入结点
    // 返回结点的个数

    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回结点 i (下标对应的数据)  0->"A"  1->"B" 2-> "C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边

    /**
     * @param v1     点的下标 (第几个顶点)  "A"-"B"  "A"->0  "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;

        numOfEdges++;
    }
}
