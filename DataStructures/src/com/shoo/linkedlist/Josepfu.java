package com.shoo.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        // 构建环形链表， 遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        circleSingleLinkedList.addBoy(50);  // 添加5个节点
        circleSingleLinkedList.showBoy();

        // 测试
        circleSingleLinkedList.countBoy(1,2,50);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点， 当前没有编号
    //private Boy first = new Boy(-1);
    private Boy first = null;

    // 添加节点，构成环形链表
    public void addBoy(int nums) {
        // nums  数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null;
        // 创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建节点
            Boy boy = new Boy(i);
            // 如果是第一个
            if (i == 1) {
                first = boy;
                first.setNext(first);

                curBoy = first;  // curBoy指向第一个
            } else {
                curBoy.setNext(boy);

                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        // first不能动，使用辅助指针完成遍历
        Boy curBoy = first;

        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());

            if (curBoy.getNext() == first) {  // 已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();  // curBoy后移
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始
     * @param CountNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */

    public void countBoy(int startNo, int CountNum, int nums) {
        //
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误，请重新输入");
            return;
        }

        // 创建辅助指针，帮助
        Boy helper = first;
        // 创建一个辅助指针 helper, 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }

            helper = helper.getNext();
        }

        // 小孩报数前，先让first 和 helper 移动 k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 小孩报数时，先让first 和 helper 指针同时 的移动 m - 1次，然后出圈
        // 循环操作，直到只有一个节点
        while (true) {
            if (helper == first) {  // 说明圈中只有一个节点
                break;
            }
            // 让first 和 helper指针同时 移动 countNum-1
            for (int j = 0; j < CountNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // first 指向的节点， 就是要出圈的小孩节点
            System.out.printf("小孩%d出圈 \n", first.getNo());

            // 将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);

        }

        System.out.printf("最后留在圈中的小孩编号 %d \n", helper.getNo());
    }
}


// 创建一个Boy类，表示一个节点

class Boy {
    private int no;   // 编号
    private Boy next;   // 指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
