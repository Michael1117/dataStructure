package com.shoo.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 添加
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        // 显示
        singleLinkedList.list();*/

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        // 测试修改
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~~");

        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表的情况~~~");
        singleLinkedList.list();

        // 删除一个节点
        singleLinkedList.del(1);
        System.out.println("删除后的链表的情况~~~");
        singleLinkedList.list();

        // 测试 单链表中有效节点的个数
        System.out.println(getLength(singleLinkedList.getHead()));

        // 测试是否得到倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);

        System.out.println("res = " + res);

        // 测试单链表的反转功能
        /*System.out.println("原来链表的情况");

        singleLinkedList.list();

        System.out.println("反转单链表~~~");
        reverseList(singleLinkedList.getHead());

        singleLinkedList.list();*/

        System.out.println("原来链表的情况");

        singleLinkedList.list();

        System.out.println("反转单链表~~~");
        reversePrint(singleLinkedList.getHead());

        //singleLinkedList.list();
    }

    // 方式2
    // 使用栈的方式来反转单链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;  // 空链表
        }

        // 创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();

        HeroNode cur = head.next;
        // 将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;  // cur后移，这样才能压入下一个节点
        }
        // 将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 方式1
    // 将单链表反转 【腾讯面试题】
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空， 或者只有一个节点， 无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助的指针(), 帮助遍历原来的链表
        HeroNode cur = head.next;

        HeroNode next = null;  // 指向当前节点【cur】的下一个节点
        HeroNode reversedHead = new HeroNode(0, "", "");

        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端

        while (cur != null) {
            next = cur.next;

            cur.next = reversedHead.next;
            reversedHead.next = cur;
            cur = next;
        }

        // 将head.next 指向  reverseHead.next ,实现单链表的反转
        head.next = reversedHead.next;

    }

    // 查找单链表中的倒数第k个节点  【新浪面试题】

    // 1. 编写一个方法，接收head节点，同时接收一个index
    // 2. index 表示是倒数第index个节点
    // 3. 先把链表从头到尾遍历， 得到链表总长度 getLength
    // 4. 得到size后， 从链表的第一个开始遍历 (size - index)个
    // 5. 如果找到了，返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 判断链表是否为空， 为空 返回null
        if (head.next == null) {
            return null;  // 没有找到
        }
        // 第一次遍历得到链表的长度 （节点个数）
        int size = getLength(head);
        // 第二次遍历 size - index 位置， 就是我们倒数的第k个节点
        // 先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }

        // 定义给辅助变量, for 循环定位到 倒数index
        HeroNode cur = head.next;  // 3
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 获取到单链表的节点的个数 (如果带头结点的链表，不统计头结点)

    /**
     * @param head 链表头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {  // 空链表
            return 0;
        }

        int length = 0;
        // 定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;   // 遍历
        }
        return length;
    }

}

// 定义SingleLinkedList  管理英雄

class SingleLinkedList {
    // 先初始化一个头节点， 头结点不动， 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;   // 返回头节点
    }

    // 添加节点到单向链表
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode) {

        // head节点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
        // 遍历链表， 找到最后
        while (true) {
            //  找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后, 将temp后移
            temp = temp.next;
        }
        // 当退出while循环时， temp就指向了链表的最后
        // 将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    // 第二种添加英雄的方式， 根据排名将英雄插入到指定位置

    public void addByOrder(HeroNode heroNode) {

        // 单链表， 因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;  // 标志添加的编号是否存在， 默认为false

        while (true) {
            if (temp.next == null) {   // 说明temp已经在链表的最后
                break;
            }

            if (temp.next.no > heroNode.no) {  // 位置找到， 就在temp后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;  // 后移，遍历当前链表
        }

        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在了， 不能加入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    // 修改节点的信息，根据no编号来修改，即no编号不能改
    // 1. 根据 newHeroNode 的 no 来修饰即可
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
        }

        // 找到需要修改的节点， 根据no编号
        HeroNode temp = head.next;
        boolean flag = false;  // 表示是否找到该节点
        while (true) {
            if (temp == null) {  // 链表的最后
                break;
            }

            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }

            temp = temp.next;
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {  // 没有找到
            System.out.printf("没有找到编号 %d 的节点，不能修改 \n", newHeroNode.no);
        }
    }

    // 1. head 不能动，因此需要一个temp辅助节点找到删除节点的前一个节点
    // 2. 说明我们在比较时，是temp.next.no 和 需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;   // 标识是否找到待删除节点

        while (true) {
            if (temp.next == null) {  // 表明已经到链表最后了
                break;
            }

            if (temp.next.no == no) {
                // 找到了待删除节点的前一个节点 temp
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {  // 说明找到
            // 可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在 \n", no);
        }
    }

    // 显示链表
    public void list() {

        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 头结点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;

        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // temp后移
            temp = temp.next;
        }
    }
}

// 定义HeroNode, 每个HeroNode 对象就是一个节点

class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;  // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方法， 我们重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    //
}
