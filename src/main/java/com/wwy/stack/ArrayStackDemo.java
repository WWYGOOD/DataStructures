package com.wwy.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(4);
//        System.out.println(arrayStack.pop());
//        arrayStack.list();
    }

    public static class ArrayStack {
        int maxSize = 0;//栈的大小
        int[] stack = null;
        int top;//栈顶

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
            top = -1;
        }

        /**
         * 判断栈是否为满
         */
        public boolean isFull() {
            if (top == maxSize - 1) {
                return true;
            }
            return false;
        }

        /**
         * 判断栈是否为空
         */
        public boolean isNull() {
            if (top == -1) {
                return true;
            }
            return false;
        }

        /**
         * 入栈
         */
        public void push(int value) {
            if (isFull()) {
                System.out.println("栈满了，无法添加");
            }
            top++;
            stack[top] = value;
        }

        /**
         * 出栈
         */
        public int pop() {
            if (isNull()) {
                throw new RuntimeException("栈为空");
            }
            int value = stack[top];
            top--;
            return value;
        }

        /**
         * 遍历栈
         */
        public void list() {
            if (isNull()) {
                System.out.println("栈为空");
            }
            while (top!=-1){
                System.out.println(stack[top]);
                top--;
            }
        }
    }
}
