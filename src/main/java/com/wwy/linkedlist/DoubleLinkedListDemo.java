package com.wwy.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        /**
         * 双线链表
         */
        Node node1 = new Node(1, "wwy");
        Node node2 = new Node(2, "jkl");
        Node node3 = new Node(3, "asd");
        Node node4 = new Node(4, "zxc");
        Node node5 = new Node(5, "wcnm");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(node1);
//        doubleLinkedList.add(node2);
//        doubleLinkedList.add(node3);
//        doubleLinkedList.add(node4);
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node5);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node4);
//        doubleLinkedList.upDate(node5);
//        doubleLinkedList.delete(node4);
        doubleLinkedList.list();
    }

    static class DoubleLinkedList {
        private Node head = new Node(0, "");

        public Node getHead() {
            return head;
        }

        /**
         * 添加节点
         */
        public void add(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            //当前节点的下一个节点指向新节点
            temp.next = node;
            //新节点的前一个节点指向当前节点
            node.pre = temp;
        }

        public void addByOrder(Node node) {
            Node temp = head;
            int status = 0;//0：下一个节点为空 1下一个节点的id大于新节点的id 2 下一个节点的id等于新节点的id
            while (true) {
                if (temp.next == null) {
                    status = 0;
                    break;
                }
                if (temp.next.id > node.id) {
                    status = 1;
                    break;
                }
                if (temp.next.id == node.id) {
                    status = 2;
                    break;
                }
                temp = temp.next;
            }
            if (status == 1) {
                //当前节点下一个节点的前节点指向新节点
                temp.next.pre = node;
                //新节点的下一个节点指向当前节点的下一个节点
                node.next = temp.next;
                //新节点的前一个节点指向当前节点
                node.pre = temp;
                //当前节点的下一个节点指向新节点
                temp.next = node;
            } else if (status == 0) {
                temp.next = node;
            } else if (status == 2) {
                System.out.println("添加节点已经存在");

            }
        }

        /**
         * 删除节点
         */
        public void delete(Node node) {
            Node temp = head;
            int status = 0;
            while (true) {
                temp = temp.next;
                if (temp == null) {
                    break;
                }
                if (temp.id == node.id) {
                    status = 1;
                    break;
                }
            }
            if (status == 1) {
                //待删除节点的前一个节点的下一个节点指向待删除节点的下一个节点
                temp.pre.next = temp.next;
                //如果待删除节点的下一个节点为null代表待删除节点为最后一个节点
                if (temp.next != null) {
                    //将待删除节点下一个节点的的前一个节点指向待删除节点的前一个节点
                    temp.next.pre = temp.pre;
                }
            } else {
                System.out.println("没有这个节点无法删除");
            }
        }

        /**
         * 修改节点
         */
        public void upDate(Node node) {
            Node temp = head;
            int status = 0;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.id == node.id) {
                    status = 1;
                    break;
                }
                temp = temp.next;
            }
            if (status == 1) {
                temp.next.name = node.name;
            } else if (status == 0) {
                System.out.println("没有这个节点无法修改");
            }
        }

        /**
         * 遍历链表
         */
        public void list() {
            Node temp = head;
            if (temp.next == null) {
                System.out.println("双线链表为空");
                return;
            }
            while (temp.next != null) {
                temp = temp.next;
                System.out.println(temp);
            }

        }
    }

    static class Node {
        private int id;
        private String name;
        public Node pre;
        public Node next;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }
}

