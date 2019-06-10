package com.shoo.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};

        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");

        //binarySortTree.infixOrder();

        // 删除叶子结点
        //binarySortTree.delNode(2);
        //binarySortTree.delNode(5);
        //binarySortTree.delNode(9);
        //binarySortTree.delNode(1);
        //binarySortTree.delNode(7);

        /*binarySortTree.delNode(10);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();*/

        System.out.println("root= " + binarySortTree.getRoot());
    }
}

// 创建二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 编写方法

    /**
     * 1. 返回 以node 为根结点的二叉排序树的最小结点的值
     * 2. 删除  以node 为根结点的二叉排序树 的最小结点的值
     *
     * @param node 传入的结点 (当做二叉排序树的跟结点)
     * @return 返回 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环的查找 左结点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }

        // target 就指向了最小结点
        // 删除最小结点
        //System.out.println(target.value);
        delNode(target.value);
        return target.value;
    }

    // 删除结点
    public void delNode(int value) {
        System.out.println(value);
        if (root == null) {
            return;
        } else {
            // 1. 需要先去找到要删除的结点 targetValue
            Node targetNode = search(value);
            //System.out.println(targetNode);
            // 没有找到
            if (targetNode == null) {
                return;
            }

            // 如果targetNode 没有父结点 整个二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 去找到targetNode 的父结点
            Node parent = searchParent(value);
            // 如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode 是父结点的左子结点 ， 还是右子结点
                if (parent.left != null && parent.left.value == value) {  // 是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {  // 是右子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两个子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                //System.out.println(minVal);
                targetNode.value = minVal;
            } else { // 删除有一个子树的结点
                // 如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果targetNode 是 parent的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {  // targetNode 是 parent的右子结点
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                } else { // 如果要删除的结点有右子结点
                    if (parent != null) {
                        //
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    // 添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;        // 如果root 为空直接让root指向node
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

// 创建Node结点

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找要删除的结点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (value == this.value) {  // 找到就是该点
            return this;
        } else if (value < this.value) {  // 如果查找的值 < 当前结点，向左子树递归查找
            // 如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { // 如果查找的值 不小于 当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }

            return this.right.search(value);
        }
    }

    // 查找要删除结点的父结点

    /**
     * @param value 要查找的结点的值
     * @return 返回的是要删除的结点的父结点， 如果没有返回null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值 < 当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);   // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);  // 向右子树递归查找
            } else {
                return null;
            }
        }
    }

    // 添加节点的方法
    // 递归添加结点，需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入结点的值， 和当前子树根结点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {  // 当前结点左子结点为null
                this.left = node;
            } else {

                // 递归向左子树添加
                this.left.add(node);
            }
        } else { // 添加的结点的值 > 当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}