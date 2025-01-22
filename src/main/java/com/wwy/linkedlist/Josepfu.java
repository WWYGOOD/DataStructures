package com.wwy.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(4);
//        circleSingleLinkedList.list();
        circleSingleLinkedList.goNode(1, 2, 4);
    }

    public static class CircleSingleLinkedList {
        Node first = null;

        /**
         * 添加环形链表
         *
         * @param num
         */
        public void addNode(int num) {
            Node curNode = null;
            for (int i = 1; i <= num; i++) {
                Node node = new Node(i);
                if (i == 1) {
                    first = node;
                    curNode = first;
                    first.next = first;
                } else {
                    curNode.next = node;
                    node.next = first;
                    curNode = node;
                }
            }
        }

        /**
         * 遍历环形链表
         */
        public void list() {
            Node node = first;
            while (true) {
                System.out.println(node.id);
                if (node.next == first) {
                    break;
                }
                node = node.next;
            }
        }

        /**
         * @param startId：从几号开始
         * @param CountNum：数几下
         * @param num：链表有多大
         */
        public void goNode(int startId, int CountNum, int num) {
//            创建辅助指针，帮助小孩完成出圈
            Node helper = first;
//            helper应该指向链表的最后
            while (true) {
                if (helper.next == first) {
                    break;
                }
                helper = helper.next;
            }
            //从第一个开始数，应该让first往前进x-1位
            for (int i = 0; i < startId - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            while (true) {
                if (helper == first) {//说明圈中只有一个节点
                    break;
                }
                for (int j = 0; j < CountNum - 1; j++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.println("出圈的是"+first.id);
                first = first.next;
                helper.next = first;
            }
            System.out.println("最后一个在圈内的是"+first.id);
        }
    }

    public static class Node {
        int id;
        Node next;

        public Node(int id) {
            this.id = id;
        }
    }
}
