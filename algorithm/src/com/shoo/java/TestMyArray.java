package com.shoo.java;

import com.shoo.util.MyArray;

public class TestMyArray {
    public static void main(String[] args) {
        // 创建一个可变的数组
        MyArray ma = new MyArray();

        int size = ma.size();
        ma.show();

        //System.out.println(size);

        // 往可变数组中添加一个元素
        ma.add(99);
        ma.add(98);
        ma.add(97);
        // 显示所有数组元素
        ma.show();
        //System.out.println(ma.size());

        // 删除某个元素
        ma.delete(1);
        ma.show();

        int element = ma.get(1);
        System.out.println(ma.get(0));
        System.out.println(element);

        System.out.println("*****************");
        ma.add(96);
        ma.add(95);
        ma.add(94);
        ma.show();

        // 插入指定位置
        ma.insert(3, 12);
        ma.show();

        System.out.println("************");

        // 替换指定位置
        ma.set(0 , 100);

        ma.show();
    }
}
