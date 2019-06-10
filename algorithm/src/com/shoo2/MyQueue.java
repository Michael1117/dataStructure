package com.shoo2;

public class MyQueue {

    int[] elements;

    public MyQueue() {
        elements = new int[0];
    }

    // 入队
    public void add(int element) {
        // 创建一个新的数组
        int[] newArr = new int[elements.length + 1];
        // 把原数组中的元素复制到新数组中

        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }

        // 把添加的元素放入新数组中
        newArr[elements.length] = element;

        // 使用新数组替换旧数组
        elements = newArr;
    }

    // 出队
    public int poll() {

        int element = elements[0];

        int[] newArr = new int[elements.length - 1];

        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = elements[i + 1];
        }

        // 替换数组
        elements = newArr;
        return element;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }
}
