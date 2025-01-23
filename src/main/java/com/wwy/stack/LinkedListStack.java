package com.wwy.stack;

public class LinkedListStack {
    public static void main(String[] args) {
       ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);

//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack.pop());
//        System.out.println(arrayStack.pop());
        arrayStack.list();
    }

    public static class ArrayStack {
        //        int[] stack = null;
        int maxSize = 0;//栈的大小
        int top=-1;//栈顶
        Node head = new Node(0);


        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
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
            Node temp = head;
            Node node = new Node(value);
            if (temp.next == null) {
                temp.next = node;
            } else {
                node.next = temp.next;
                temp.next = node;
            }
            top++;
        }

        /**
         * 出栈
         */
        public int pop() {
            if (isNull()) {
                throw new RuntimeException("栈为空");
            }
            Node temp = head;
            head.next = head.next.next;
            top--;
            return temp.next.id;
        }

        /**
         * 遍历栈
         */
        public void list() {
            if (isNull()) {
                System.out.println("栈为空");
            }
            Node temp = head;
            while (top != -1) {
                System.out.println(temp.next);
                temp=temp.next;
                top--;
            }
        }
    }

    public static class Node {
        int id;
        Node next;

        public Node(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }


}
