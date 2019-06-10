package com.shoo2.test;

import com.shoo2.MyStack;

public class TestMystack {
    public static void main(String[] args) {
        // 创建一个栈
        MyStack ms = new MyStack();

        ms.push(9);
        ms.push(8);
        ms.push(7);

        int pop = ms.pop();
        System.out.println(pop);
        System.out.println(ms);

        //查看栈顶元素
        System.out.println(ms.peek());

        System.out.println(ms.isEmpty());
    }
}
