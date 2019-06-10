package com.shoo.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        // 创建一个ArrBinaryTree
        ArrayBinaryTree arrBinaryTree = new ArrayBinaryTree(arr);

        //arrBinaryTree.preOrder(); // 1 2 4 5 3 6 7
        arrBinaryTree.infixOrder();
    }
}


// 编写ArrayBinaryTree, 实现顺序存储二叉树遍历

class ArrayBinaryTree {
    private int[] arr;  // 存储数据结点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    // 重载preOrder
    public void infixOrder() {
        this.infixOrder(0);
    }
    // 编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     * @param index 数组下标
     */
    public void preOrder(int index) {
        // 数组为空， 或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length)
            preOrder(2 * index + 1);  // 0 1 3 5 7

        if ((index * 2 + 2) < arr.length)
            preOrder(2 * index + 2);  // 2 4 6
    }

    public void infixOrder(int index) {
        // 数组为空， 或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }

        // 向左递归遍历
        if ((index * 2 + 1) < arr.length)
            infixOrder(2 * index + 1);  // 0 1 3 5 7
        // 输出当前这个元素
        System.out.println(arr[index]);

        if ((index * 2 + 2) < arr.length)
            infixOrder(2 * index + 2);  // 2 4 6
    }
}