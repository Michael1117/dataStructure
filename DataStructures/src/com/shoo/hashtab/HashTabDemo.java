package com.shoo.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 添加雇员");
            System.out.println("find : 查找雇员");
            System.out.println("exit: 添加雇员");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();

                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

// 创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;  // 多少条链表

    // 构造器
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];

        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        // 根据id ,得到该员工应该添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    // 根据输入的id，查找雇员
    public void findEmpById(int id) {
        // 使用散列函数确定哪条链表查找
        int empLinkedListNO = hashFun(id);

        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);

        if (emp != null) {
            System.out.printf("在第 %d 条链表中找到雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("没有找到！");
        }
    }

    // 遍历所有的链表， 遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public int hashFun(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;

    }
}

// 创建EmpLinkedList, 表示链表

class EmpLinkedList {
    // 头指针
    private Emp head;   // null

    public void add(Emp emp) {
        // 添加第一个
        if (head == null) {
            head = emp;
            return;
        }

        //
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;  // 后移
        }

        curEmp.next = emp;
    }

    // 遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }

        System.out.print("第" + (no + 1) + "链表的信息为");
        Emp curEmp = head;  // 辅助指针
        while (true) {
            System.out.printf("=> id = %d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {  // 已经是最后节点
                break;
            }

            curEmp = curEmp.next;  // 后移，遍历
        }
        System.out.println();
    }

    // 根据id 查找雇员
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        // 辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {  // 找到
                break;
            }

            if (curEmp.next == null) {  // 没有找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;  // 后移
        }

        return curEmp;
    }
}