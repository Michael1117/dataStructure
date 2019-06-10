package com.shoo.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        //String str2 = "BBC";
        int[] next = kmpNext("ABCDABD");
        System.out.println("next = " + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
    }

    // kmp 搜索算法

    /**
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return 返回第一个匹配的位置，-1 没有匹配，
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {  // 找到了  j = 3  i =
                return i - j + 1;
            }
        }
        return -1;
    }

    // 获取一个字符串 (子串) 的部分匹配值表
    public static int[] kmpNext(String dest) {

        // 创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; // 如果字符串是长度为1 部分匹配值是0

        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j) 时，需要从next[j-1] 获取新的j
            // 直到 dest.charAt(i) == dest.charAt(j) 才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            // 当dest.charAt(i) == dest.charAt(j) 满足时，部分配置值就是 + 1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        return next;
    }
}
