package com.wwy.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        /**
         * 单向链表
         *
         */
        HeroNode node1 = new HeroNode(1, "wwy");
        HeroNode node2 = new HeroNode(2, "asd");
        HeroNode node3 = new HeroNode(3, "ghjg");
        HeroNode node4 = new HeroNode(4, "oik");
        HeroNode node5 = new HeroNode(4, "wcnm");

        LinkdeNode linkdeNode = new LinkdeNode();
//        linkdeNode.add(node1);
//        linkdeNode.add(node3);
//        linkdeNode.add(node2);
//        linkdeNode.add(node4);
        linkdeNode.addByOrder(node1);
        linkdeNode.addByOrder(node3);
        linkdeNode.addByOrder(node2);
        linkdeNode.addByOrder(node4);

        System.out.println("--------修改前的节点--------------------------");
        linkdeNode.list();
        System.out.println("--------修改后的节点--------------------------");
        linkdeNode.upData(node5);
        linkdeNode.list();
        System.out.println("--------删除后的节点--------------------------");
        linkdeNode.delete(node3);
        linkdeNode.delete(node4);
        linkdeNode.delete(node2);
        linkdeNode.list();


    }

    public static class LinkdeNode {
        private HeroNode head = new HeroNode(0, "");

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
                temp=temp.next;
            }
            if (status == 1) {
                temp.next = temp.next.next;
            }else if (status==0){
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
