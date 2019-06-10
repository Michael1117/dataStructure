package com.shoo.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);

        // 测试
        preOrder(root);
    }

    // 前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }else {
            System.out.println("是空树，不能遍历~~");
        }
    }

    // 创建霍夫曼树的方法

    /**
     *
     * @param arr  需要创建成霍夫曼树的数组
     * @return  创建好后的霍夫曼树root结点
     */
    public static Node createHuffmanTree(int[] arr) {
        // 1.遍历 arr 数组
        // 2. 将arr的每一个元素构成一个Node
        // 3. 将Node 放入 ArrayList中

        List<Node> nodes = new ArrayList<Node>();

        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            // 取出根结点权值最小的两颗二叉树
            // (1) 取出权值最小的节点  (二叉树)
            Node leftNode = nodes.get(0);

            // (2) 取出权值第二小的结点 ( 二叉树 )

            Node rightNode = nodes.get(1);

            // (3) 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // (4) 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // (5) 将parent加入nodes

            nodes.add(parent);

            //System.out.println("第一次处理后" + nodes);

        }
        // 返回霍夫曼树的root结点
        return nodes.get(0);
    }
}


// 创建结点类

// 为了让Node 对象持续排序Collections 集合排序
// 让 Node 实现Comparable接口
class Node implements Comparable<Node> {
    int value;  // 结点权值
    Node left;  // 左子结点
    Node right;  // 右子结点

    // 前序遍历
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {

        //  从小到大排序
        return this.value - o.value;

        //return -(this.value - o.value);  // 从大到小排序
    }
}