package com.shoo.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");


        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        // 测试
        /*System.out.println("前序遍历");
        binaryTree.preOrder();

        // 测试
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        // 测试
        System.out.println("后序遍历");
        binaryTree.postOrder();*/

        // 前序遍历 查找
       /* System.out.println("前序遍历查找~~");

        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        }else {
            System.out.printf("没有找到 no = %d 的英雄" , 5);
        }
*/
        // 中序遍历 查找
        /*System.out.println("中序遍历查找~~");

        HeroNode resNode = binaryTree.infixOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        }else {
            System.out.printf("没有找到 no = %d 的英雄" , 5);
        }*/

        // 后序遍历 查找
        /*System.out.println("后序遍历查找~~");

        HeroNode resNode = binaryTree.postOrderSearch(3);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 3);
        }*/

        // 测试删除节点

        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(5);   // debug
        //binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");

        binaryTree.preOrder(); // debug
    }
}

// 定义binaryTree  二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    // 删除结点
    public void delNode(int no) {
        if (root != null) {
           // 如果只有一个root结点，须判断root是不是要删除的节点
           if (root.getNo() == no) {
               root = null;
           }else {
               // 递归删除
               root.delNode(no);
           }
        }else {
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

// 创建HeroNode 节点

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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
