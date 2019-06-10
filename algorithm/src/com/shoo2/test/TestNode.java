package com.shoo2.test;

import com.shoo2.Node;

public class TestNode {
    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);


        // 追加节点

        /**
         * null
         * com.shoo2.Node@1540e19d
         * null
         * com.shoo2.Node@1540e19d
         * com.shoo2.Node@677327b6
         * null
         */
        n1.append(n2).append(n3).append(new Node(4));



        //n1.append(n3).append(new Node(4));

        //System.out.println(n1);
        //System.out.println(n1.next());

        //System.out.println(n1.next().next());
        // 取出下一个节点
        //System.out.println(n1.next().next().getData());

        // 判断是否为最后一个节点
        //System.out.println(n1.next().next().next().isLast());

        // 显示所有节点内容
        //n1.show();

        // 删除一个节点
        //n1.next().removeNext();

        // 显示所有节点内容
        //n1.show();

        // 插入一个新节点
        //Node node = new Node(5);
        //n1.next().after(node);

        //n1.show();
        //n1.removeNext();
        n1.show();
    }
}
