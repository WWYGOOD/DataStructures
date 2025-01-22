package com.wwy.stack;

public class Calculator {
    public static void main(String[] args) {
        ArrayStack numStack = new ArrayStack(100);
        ArrayStack operStack = new ArrayStack(100);
        String exp = "5-2*5+10"; // 修改为需要计算的表达式
        int chCount = 0;

        for (char ch : exp.toCharArray()) {
            if (operStack.isOper(ch)) {
                // 当前操作符优先级小于等于栈顶操作符时，先计算栈中的操作
                while (!operStack.isNull() && operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    char oper = (char) operStack.pop();
                    int res = numStack.cal(num1, num2, oper);
                    numStack.push(res);
                }
                // 将当前操作符压入栈中
                operStack.push(ch);
            } else {
                // 将数字字符转换为整数并压入数字栈
                numStack.push(ch - '0');
            }
            chCount++;
        }

        // 处理剩余的操作符
        while (!operStack.isNull()) {
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            char oper = (char) operStack.pop();
            int res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        // 输出最终结果
        System.out.println("Result: " + numStack.pop());
    }

    public static class ArrayStack {
        private final int maxSize;
        private final int[] stack;
        private int top = -1;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isNull() {
            return top == -1;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("栈满了，无法添加");
            }
            stack[++top] = value;
        }

        public int pop() {
            if (isNull()) {
                throw new RuntimeException("栈为空");
            }
            return stack[top--];
        }

        public int peek() {
            if (isNull()) {
                throw new RuntimeException("栈为空");
            }
            return stack[top];
        }

        public int priority(char oper) {
            switch (oper) {
                case '+':
                case '-':
                    return 0;
                case '*':
                case '/':
                    return 1;
                default:
                    return -1;
            }
        }

        public boolean isOper(char ch) {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/';
        }

        public int cal(int num1, int num2, char oper) {
            switch (oper) {
                case '+':
                    return num1 + num2;
                case '-':
                    return num1 - num2;
                case '*':
                    return num1 * num2;
                case '/':
                    if (num2 == 0) {
                        throw new ArithmeticException("除数不能为0");
                    }
                    return num1 / num2;
                default:
                    throw new IllegalArgumentException("非法运算符");
            }
        }
    }
}