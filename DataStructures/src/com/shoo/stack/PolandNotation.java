package com.shoo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 中缀表达式 => 后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);

        System.out.println("中缀表达式对应的List" + infixExpressionList);

        List<String> suffixExpressionList = parseSuffixExpression(infixExpressionList);

        System.out.println("后缀表达式对应的List" + suffixExpressionList);

        System.out.printf("expression=%d", calculate(suffixExpressionList));
        // 逆波兰表达式
        // (3+4)*5-6  => 34+5*6-
        /*String suffixExpression = "3 4 + 5 * 6 -";

        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList= " + list);

        int res = calculate(list);
        System.out.println("计算的结果 =" + res);*/
    }

    public static List<String> parseSuffixExpression(List<String> ls) {
        // 定义两个栈
        Stack<String> s1 = new Stack<String>();
        //Stack<String> s2 = new Stack<String>();

        List<String> s2 = new ArrayList<String>();

        // 遍历ls
        for (String item : ls) {
            // 如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();  // !!! 将 ( 弹出s1栈，消除小括号
            } else {
                // 当item的优先级 <= 栈顶运算符的优先级
                //
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符依次弹出加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;  //

    }

    // 中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List, 存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;  // 指针，用于遍历 中缀表达式字符串
        String str; // 对多位数拼接
        char c;   // 每遍历一个字符，就放入到c
        do {
            // 如果c是一个非数字，加入ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {  // ASCII 码
                ls.add("" + c);
                i++;  // i后移

            } else {
                // 数字 需要考虑多位数的问题
                str = "";  // 先将str置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) < 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());

        return ls;
    }

    // 将一个逆波兰表达式，依次将数据和运算符 放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression 分割
        String[] split = suffixExpression.split(" ");

        List<String> list = new ArrayList<String>();

        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式运算
    public static int calculate(List<String> ls) {
        // 创建栈
        Stack<String> stack = new Stack<String>();

        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                // pop
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;  // 先进后出
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {

                    throw new RuntimeException("运算符有误");
                }

                // res 入栈
                stack.push(res + "");
            }
        }

        return Integer.parseInt(stack.pop());
    }
}

// 编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    // 写一个方法， 返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }

        return result;
    }
}