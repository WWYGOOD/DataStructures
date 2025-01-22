package com.wwy.queue;

import java.util.Scanner;

public class CircleArrarQueueDemo {
    public static void main(String[] args) {
        /**
         * 环形队列
         */
        Queue queue = new Queue(3);
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.println("s(show),获取队列所有数据");
            System.out.println("a(add),向队列添加数据");
            System.out.println("g(get),获取队列数据");
            System.out.println("h(head),获取队列前端数据");
            System.out.println("e(exit),退出");
            char in = sc.next().charAt(0);
            switch (in) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加到队列的数据");
                    int num = sc.nextInt();
                    queue.addQueue(num);
                    break;
                case 'g':
                    try {
                        int queue1 = queue.getQueue();
                        System.out.println(queue1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int i = queue.headQueue();
                        System.out.println(i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    System.out.println("退出");
                    flag = false;
                    break;
            }
        }
    }

    static class Queue {
        private int maxSize;
        private int front;
        private int rear;
        private int[] arr;

        public Queue(int maxSize) {
            this.maxSize = maxSize;//队列的最大容量
            rear = 0;//队列的尾端+1
            front = 0;//队列的第一个元素
            arr = new int[maxSize];//数组模拟的队列
        }

        //检查队列是否装满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //检查队列是否为空
        public boolean isNull() {
            return front == rear;
        }

        //向队列添加数据
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列满了，无法添加");
                return;
            }
            arr[rear] = n;
            rear = (rear + 1) % maxSize;
        }

        //获取队列数据,出队列
        public int getQueue() {
            if (isNull()) {
                throw new RuntimeException("队列为空");
            }
            int temp = arr[front];
            front = (front + 1) % maxSize;
            return temp;
        }

        //获取队列所有数据
        public void showQueue() {
            if (isNull()) {
                System.out.println("队列为空");
                return;
            }
            for (int i = front; i < front + size(); i++) {
                System.out.println("arr[" + (i) % maxSize + "]=" + arr[(i) % maxSize]);
            }
        }

        //获取队列前端数据
        public int headQueue() {
            if (isNull()) {
                throw new RuntimeException("队列为空");
            }
            return arr[front];
        }

        public int size() {
            return (rear + maxSize - front) % maxSize;
        }
    }
}
