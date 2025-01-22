package com.wwy.linkedlist;


import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        /**
         * 单向链表
         *
         */
//        HeroNode node1 = new HeroNode(1, "wwy");
//        HeroNode node2 = new HeroNode(3, "asd");
//        HeroNode node3 = new HeroNode(5, "ghjg");
//        HeroNode node5 = new HeroNode(4, "wcnm");
//
//        LinkedList linkdeList = new LinkedList();
//        linkdeNode.add(node1);
//        linkdeNode.add(node3);
//        linkdeNode.add(node2);
//        linkdeNode.add(node4);
//        linkdeList.addByOrder(node1);
//        linkdeList.addByOrder(node3);
//        linkdeList.addByOrder(node2);

//        System.out.println("--------修改前的节点--------------------------");
//        linkdeList.list();
//        System.out.println("--------修改后的节点--------------------------");
//        linkdeNode.upData(node5);
//        linkdeNode.list();
//        System.out.println("--------删除后的节点--------------------------");
//        linkdeNode.delete(node3);
//        linkdeNode.delete(node4);
//        linkdeNode.delete(node2);
//        linkdeNode.list();
//        System.out.println("链表的长度" + linkdeList.size());

//        System.out.println("获取倒数第3个节点" + getK(linkdeList, 3));

//        LinkdeNode linkdeNode1 = reverseLinkedList(linkdeList);
//        linkdeNode1.list();
//
//        reversePrintLinkedList(linkdeList);


        HeroNode nodea1 = new HeroNode(1, "wwy");
        HeroNode nodea2 = new HeroNode(3, "asd");
        HeroNode nodea3 = new HeroNode(5, "ghjg");

        HeroNode nodeb1 = new HeroNode(2, "wwy");
        HeroNode nodeb2 = new HeroNode(4, "asd");
        HeroNode nodeb3 = new HeroNode(6, "ghjg");

        LinkedList aLinkedList = new LinkedList();
        LinkedList bLinkedList = new LinkedList();

        aLinkedList.addByOrder(nodea1);
        aLinkedList.addByOrder(nodea2);
        aLinkedList.addByOrder(nodea3);

        bLinkedList.addByOrder(nodeb1);
        bLinkedList.addByOrder(nodeb2);
        bLinkedList.addByOrder(nodeb3);
//        aLinkedList.list();
//        bLinkedList.list();
        LinkedList unLinkedList = unLinkedList(aLinkedList, bLinkedList);
        unLinkedList.list();
    }

    /**
     * 合并两个单链表，合并后依然有序
     */
    public static LinkedList unLinkedList(LinkedList aList, LinkedList bList) {
        LinkedList newLinedList = new LinkedList();
        HeroNode aCur = aList.getHead().next;
        HeroNode bCur = bList.getHead().next;

        // 合并两个链表，保持有序性
        while (aCur != null && bCur != null) {
                // 创建新节点以避免影响原始链表
                newLinedList.addByOrder(new HeroNode(aCur.id, aCur.name));
                aCur = aCur.next;
                newLinedList.addByOrder(new HeroNode(bCur.id, bCur.name));
                bCur = bCur.next;
        }

        // 如果aList还有剩余元素，则全部加入新链表
        while (aCur != null) {
            newLinedList.addByOrder(new HeroNode(aCur.id, aCur.name));
            aCur = aCur.next;
        }

        // 如果bList还有剩余元素，则全部加入新链表
        while (bCur != null) {
            newLinedList.addByOrder(new HeroNode(bCur.id, bCur.name));
            bCur = bCur.next;
        }

        return newLinedList;
    }

    /**
     * 打印反转单链表
     */
    public static void reversePrintLinkedList(LinkedList linkdeNode) {
        HeroNode cur = linkdeNode.getHead().next;
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 反转单链表
     */
    public static LinkedList reverseLinkedList(LinkedList linkdeNode) {
        HeroNode temp = linkdeNode.getHead();
        HeroNode resList = new LinkedList().getHead();
        HeroNode cur = temp.next;
        HeroNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = resList.next;
            resList.next = cur;
            cur = next;
        }
        temp.next = resList.next;
        return linkdeNode;
    }

    /**
     * 获取倒数第k个节点
     *
     * @param linkdeNode
     * @param index
     * @return
     */
    public static HeroNode getK(LinkedList linkdeNode, int index) {
        int size = linkdeNode.size();
        if (index > size || index < 0) {
            System.out.println("不存在这个节点");
            return null;
        }
        int num = size - index;
        HeroNode node = linkdeNode.getHead().next;
        if (num == 0) {
            return node;
        }
        for (int i = 0; i < num; i++) {
            node = node.next;
        }
        return node;
    }

    public static class LinkedList {
        private HeroNode head = new HeroNode(0, "");

        public HeroNode getHead() {
            return head;
        }

        public void setHead(HeroNode head) {
            this.head = head;
        }

        /**
         * 添加节点
         *
         * @param heroNode
         */
        public void add(HeroNode heroNode) {
            //创建临时节点
            HeroNode temp = head;
            //遍历链表，找到最后
            while (true) {
                //如果next为空，表示最后一个节点
                if (temp.next == null) {
                    break;
                }
                //如果不为空，将temp后移
                temp = temp.next;
            }
            //退出循环代表找到了最后一个节点
            //将当前节点连接到下一个节点
            temp.next = heroNode;
        }

        /**
         * 根据id排序添加节点
         *
         * @param heroNode
         */
        public void addByOrder(HeroNode heroNode) {
            //创建临时节点
            HeroNode temp = head;
            boolean flag = false;
            while (true) {
                //如果节点的下一个节点为空，退出循环
                if (temp.next == null) {
                    break;
                }
                //如果下一个节点的id大于新节点id，退出循环
                if (temp.next.id > heroNode.id) {
                    break;
                }
                //如果下一个节点的id等于新节点的id退出循环
                if (temp.next.id == heroNode.id) {
                    flag = true;
                    break;
                }
                //后移节点
                temp = temp.next;
            }
            //如果推出循环，判断flag和是否为空
            if (flag) {
                System.out.println("无法添加，链表中已经有当前节点" + temp.next);
            } else if (temp.next == null) {
                temp.next = heroNode;
            } else if (temp.next.id > heroNode.id) {
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        /**
         * 修改节点信息
         *
         * @param newHeroNode
         */
        public void upData(HeroNode newHeroNode) {
//            创建临时节点
            HeroNode temp = head;
            boolean flag = false;
            //如果为空，当前链表为空
            if (temp.next == null) {
                return;
            }
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.id.equals(newHeroNode.id)) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag == true) {
                temp.name = newHeroNode.name;
            } else {
                System.out.println("没有找到这个节点，无法修改");
            }
        }

        /**
         * 删除节点
         *
         * @param heroNode
         */
        public void delete(HeroNode heroNode) {
            //创建临时节点
            HeroNode temp = head;
            int status = 0;//0：未找到待删除节点 1：找到待删除节点
            boolean flag = false;
            //如果为空，当前链表为空
            if (temp.next == null) {
                return;
            }
            while (true) {
                if (temp.next == null) {
                    status = 0;
                    break;
                }
                if (temp.next.id == heroNode.id) {
                    status = 1;
                    break;
                }
                temp = temp.next;
            }
            if (status == 1) {
                temp.next = temp.next.next;
            } else if (status == 0) {
                System.out.println("没有找到当前节点，无法删除");
            }
        }

        /**
         * 遍历链表
         */
        public void list() {
            //如果为空，当前链表为空
            if (head.next == null) {
                return;
            }
            //创建临时节点
            HeroNode temp = head;
            while (true) {
                //后移临时节点
                temp = temp.next;
                System.out.println(temp);
                //如果当前节点的下一个节点为空，退出循环
                if (temp.next == null) {
                    break;
                }
            }
        }

        /**
         * 获取链表的长度，不计算头节点
         */
        public int size() {
            HeroNode temp = head;
            int length = 0;
            if (temp.next == null) {
                System.out.println("链表为空");
            }
            while (true) {
                temp = temp.next;
                if (temp != null) {
                    length++;
                } else {
                    break;
                }
            }
            return length;
        }
    }

    public static class HeroNode {
        private Integer id;
        private String name;
        private HeroNode next;

        public HeroNode(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "HeroNode{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }
}
