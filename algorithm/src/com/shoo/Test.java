package com.shoo;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet<String> hashSet1 = new HashSet<String>();
        HashSet<String> hashSet2 = new HashSet<String>();

        hashSet1.add("1");

        hashSet1.add("2");
        hashSet1.add("100");

        hashSet2.add("1");
        hashSet2.add("2");
        hashSet2.add("200");

        hashSet1.retainAll(hashSet2);   // hashSet2和hashSet1共有的部分赋值给hashSet1

        System.out.println(hashSet1);   // 1,2
    }
}
