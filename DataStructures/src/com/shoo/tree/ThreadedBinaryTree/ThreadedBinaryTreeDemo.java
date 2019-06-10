package com.shoo.tree.ThreadedBinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "Jim");

        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        // 测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();

        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //  10号节点作为测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();

        System.out.println("10号结点的前驱结点" + leftNode);  // 3
        System.out.println("10号结点的后继结点" + rightNode); // 1


        System.out.println("使用线索化的方式 遍历线索化二叉树");

        threadedBinaryTree.threadedList(); // 8 3 10 1 14 6
    }
}

// 定义ThreadedBinaryTree  实现了线索化二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    // 为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    // pre 在递归进行线索化时，总是保留前一个结点
    private HeroNode pre = null;

    // 递归进行线索化时，pre总是保留前一个结点
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 重载threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    // 遍历线索化二叉树的方法
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while (node != null) {
            // 循环找到leftType == 1的结点，第一个找到的就是8结点
            // 后面随着遍历而变化，当leftType == 1时，说明该结点是按照线索化
            // 处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            // 对应当前结点
            System.out.println(node);

            // 如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            // 替换这个遍历的结点
            node = node.getRight();
        }
    }

    // 编写对二叉树进行中序线索化的方法

    /**
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //
        if (node == null) {
            return;
        }

        // 1. 先线索化左子树
        threadedNodes(node.getLeft());
        // 2. 线索化当前结点

        // 处理当前结点的前驱结点
        if (node.getLeft() == null) {

            // 让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前结点的左指针类型
            node.setLeftType(1);  // 指向的是前驱结点
        }

        // 处理后继结点
        if (pre != null && pre.getRight() == null) {
            // 让前驱结点的右指针指向当前结点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }

        //  每处理一个结点后，让当前结点是下一个节点的前驱结点
        pre = node;
        // 3. 线索化右子树
        threadedNodes(node.getRight());
    }

    // 删除结点
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root结点，须判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序遍历
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序遍历

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

// 创建HeroNode

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // leftType==0 表示指向的是左子树，如果是1则表示指向前驱结点
    // rightType==0 表示指向是右子树，如果是1 表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 递归删除结点
    public void delNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        // 右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);   // 输出父结点

        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }

        // 递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        // 递归变量左子树
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        // 递归变量左子树
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    // 前序遍历查找

    /**
     * @param no 查找no
     * @return 如果找到就返回该Node没有返回null
     */

    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {  // 说明在左子树找到
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {


        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}

