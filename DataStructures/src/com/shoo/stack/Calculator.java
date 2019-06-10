package com.shoo.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-8";
        // 创建两个栈， 数栈、符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        // 定义需要相关变量
        int index = 0;  // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  // 每次扫描得到的char保存到ch中
        String keepNum = "";  // 用于拼接多位数

        while (true) {
            // 依次得到express中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);

            // 判断ch是什么， 然后做相应的处理
            if (operStack.isOper(ch)) {

                if (!operStack.isEmpty()) {  // 符号栈不为空
                    // 处理  当前操作符的优先级 <= 栈中的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算的结果入数栈
                        numStack.push(res);
                        // 将当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 当前操作符的优先级 > 栈中的操作符
                        operStack.push(ch);
                    }
                } else {
                    // 如果符号栈为空直接入符号栈
                    operStack.push(ch);
                }
            } else { // 如果是数，直接入栈
                //numStack.push(ch - 48);  // ASCII 字符 1是49
                keepNum += ch;

                // 如果ch是最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }
                //  如果是操作符 就直接入栈
                else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {

                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            // 让index + 1, 并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 扫描完毕， 取出符号和数字，并运行
        while (true) {
            //
            if (operStack.isEmpty()) {
                break;
            }

            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        //
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }
}


// 先创建一个栈

// 表示栈
class ArrayStack2 {
    private int maxSize;  // 栈的大小
    private int[] stack;   // 数组，数组模拟栈
    private int top = -1;  //

    // 构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public int peek() {
        return stack[top];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈  --- push
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;

    }

    // 出栈  -- pop
    public int pop() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈的情况
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符优先级， 优先级使用数字表示，数字越大则 优先级越高
    public int priority(int oper) {
        // 假定只有 + - * /
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // 存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:

                break;
        }

        return res;
    }
}
