# 数据结构与算法

## 线性结构和非线性结构

### 1. 线性结构

- 线性结构作为最常用的数据结构，其特点是**数据元素之间存在一对一**的线性关系
- 线性结构有两种不同的存储结构，即**顺序**存储结构和**链式**存储结构，顺序存储结构的线性表成为**顺序表**，顺序表中的存储元素是连续的
- 链式存储的线性表称为链表，链表中的存储元素**不一定**是连续的，元素节点中存放数据元素以及**相邻元素**的地址信息
- 常见的线性结构：数组，队列，链表，栈

### 2.非线性结构

常见的非线性结构：二维数组，多维数组，广义表，树结构，图结构

## 稀疏数组和队列

### 稀疏数组---SparseArray

**背景：**

如下图所示，这里有一个棋盘，如果现在要让你通过编码的方式，让你将这盘棋局保存起来

![image-20250122173743654](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122173743654.png)



**应用实例**

二维数组转稀疏数组的思路

1. 遍历原始的二维数组，得到有效数据的个数sum
2. 根据sum就可以创建稀疏数组SparseArray ` int[sum+1][3]`
3. 将二维数组的有效数据存入到稀疏数组中

稀疏数组转原始的二维数组的思路

1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组即可

![image-20250122173308368](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122173308368.png)

**代码实现**

~~~java
package com.wwy.sparsearray;

import java.io.IOException;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        /**
         *稀疏数组
         */
//        创建一个原始的二维数组11*11表示棋盘
        int[][] cheese = new int[11][11];
        cheese[0][1] = 1;
        cheese[2][3] = 2;
        listArr(cheese);
/**
 * 设置稀疏数组
 * 1.遍历原始数组，查询有多少有效数字
 * 2.创建稀疏数组，int[有效数字+1][3]
 * 3.将原始数组的行列数，以及有效数字数量赋值到稀疏数组的第一行[0]
 * 4.将有效数字的行列信息以及值复制到稀疏数组第二列往后
 */
        int num = 0;//有效数字
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (cheese[i][j] != 0) {
                    num++;
                }
            }
        }
        int[][] sparseArray = new int[num + 1][3];
        sparseArray[0][0] = cheese[0].length;
        sparseArray[0][1] = cheese.length;
        sparseArray[0][2] = num;
        int sparseCount = 1;//稀疏数组计数器
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (cheese[i][j] != 0) {
                    sparseArray[sparseCount][0] = i;
                    sparseArray[sparseCount][1] = j;
                    sparseArray[sparseCount][2] = cheese[i][j];
                    sparseCount++;
                }
            }
        }


        System.out.println("-----得到的稀疏数组----------------------------------------------------------------");
        listArr(sparseArray);
        System.out.println("-------将稀疏数组恢复成二维数组------------------------------------------------");
/**
 *         1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
 *         2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
 */
        int row = sparseArray[0][0];
        int column = sparseArray[0][1];
        int[][] cheese1 = new int[row][column];

        for (int i = 1; i < sparseArray.length; i++) {
            int cheese1Row = sparseArray[i][0];
            int cheese1Column = sparseArray[i][1];
            int value = sparseArray[i][2];
            cheese1[cheese1Row][cheese1Column] = value;
        }
        listArr(cheese1);
    }

    //打印数组
    public static void listArr(int[][] arr) {
        for (int[] row : arr) {
            for (int data : row) {
                System.out.print(data);
            }
            System.out.println();
        }
    }
}
~~~

### 队列---ArrayQueue

**介绍**：

- 队列是一个有序列表，可以用数组或是链表来实现
- 遵循先入先出的原则，即：先存入队列的数据要先取出，后存入队列的要后取出

![image-20250122174618563](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122174618563.png)

#### **数组模拟队列**

- 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图，其中 maxSize 是该队列的最大容量。
- 因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front及 rear分别记录队列前后端的下标，front会随着数据输出而改变，而 rear则是随着数据输入而改变，如图所示:

![image-20250122175021171](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122175021171.png)

**代码实现**

~~~java
package com.wwy.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    /**
     * 队列
     * @param args
     */
    public static void main(String[] args) {
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
            rear = -1;//队列的尾端
            front = -1;//队列的前端
            arr = new int[maxSize];//数组模拟的队列
        }

        //检查队列是否装满
        public boolean isFull() {
            return rear == (maxSize - 1);
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
            rear++;
            arr[rear] = n;
        }

        //获取队列数据,出队列
        public int getQueue() {
            if (isNull()) {
                throw new RuntimeException("队列为空");
            }
            front++;
            return arr[front];
        }

        //获取队列所有数据
        public void showQueue() {
            if (isNull()) {
                System.out.println("队列为空");
                return;
            }
            for (int data : this.arr) {
                System.out.println(data);
            }
        }

        //获取队列前端数据
        public int headQueue() {
            if (isNull()) {
                throw new RuntimeException("队列为空");
            }
            return arr[front + 1];
        }
    }
}
~~~

#### 数组模拟循环队列

**介绍：**为了解决“假溢出”现象，使得队列的存储空间得到充分利用，一个巧妙的方法就是将顺序队列的数组看成一个头尾相接的循环结构

队列的头尾相接的顺序存储结构称为***\*循环队列\****。

![image-20250122175240387](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122175240387.png)

**代码实现**

~~~java
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

~~~

## 链表---LinkedList

### 单向链表---LinkedList

链表是有序的列表

1. 链表是以节点的方式来存储，是链式存储

2. 每个节点包含data域，next域，next用来指向下一个地址

3. 链表分带头节点和不带头节点的链表![image-20250122200857627](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122200857627.png)

   代码实现

   ~~~java
   package com.wwy.linkedlist;
   
   
   import java.util.Stack;
   
   public class SingleLinkedListDemo {
       public static void main(String[] args) {
           /**
            * 单向链表
            *
            */
   
   
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
   ~~~

### 双向链表---DoubleLinkedList

- 单向链表的缺点分析：
  - 单向链表查找只能是一个方向，而双向链表可以向前或者向后查找
  - 单向链表不能实现自我删除，需要依靠辅助节点，而双向链表可以自我删除


![image-20250122201257254](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122201257254.png)

**代码实现**

~~~java
package com.wwy.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        /**
         * 双线链表
         */

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

~~~

### 循环链表---CricleLinkedList

**应用案例 **：约瑟夫问题

已知 n 个人（以编号1，2，3 … n 分别表示）围成一圈。从编号为 1 的人开始报数，数到 m 的那个人出列；他的下一个人又从 1 开始报数，数到 m 的那个人又出列；依此规律重复下去，直到最后剩下一个人。要求找出最后出列的人的编号


![image-20250122202403877](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122202403877.png)

**代码实现**

~~~java
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

~~~

## 栈---Stack

### 1. 栈的定义

**栈**`stack`：是只允许在一端进行插入或删除的线性表。首先栈是一种线性表,但限定这种线性表只能在某一端进行插入和删除操作。

![image-20250122203007823](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250122203007823.png)

**栈顶**（Top）：线性表允许进行插入删除的那一端。
**栈底**（Bottom）：固定的，不允许进行插入和删除的另一端。
**空栈**：不含任何元素的空表。

**栈又称为后进先出（Last In First Out）的线性表，简称LIFO结构**

### 3. 栈的常见基本操作

InitStack(&S)：初始化一个空栈S。
StackEmpty(S)：判断一个栈是否为空，若栈为空则返回true，否则返回false。
Push(&S, x)：进栈（栈的插入操作），若栈S未满，则将x加入使之成为新栈顶。
Pop(&S, &x)：出栈（栈的删除操作），若栈S非空，则弹出栈顶元素，并用x返回。
GetTop(S, &x)：读栈顶元素，若栈S非空，则用x返回栈顶元素。
DestroyStack(&S)：栈销毁，并释放S占用的存储空间（“&”表示引用调用）。

### 3. 栈的应用场景

- 子程序的调用:在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以回到原来的程序中。
- 处理递归调用:和子程序的调用类似，只是除了储存下一个指令的地址外，也将参数、区域变量等数据存入堆栈中。
- 表达式的转换[中缀表达式转后缀表达式]与求值(实际解决)。
- 二叉树的遍历。
- 图形的深度优先(depth- first)搜索法。

**代码实现---数组模拟实现**

~~~java
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

~~~

**代码实现---链表模拟实现**

~~~java
package com.wwy.stack;

public class LinkedListStack {
    public static void main(String[] args) {
       ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
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
~~~

### 4.前缀，中缀，后缀表达式(逆波兰表达式)

**前缀表达式：**

前缀表达式又称波兰表达式，前缀表达式的运算符位于操作数之前

例如：(3+4)×5-6 对应的前缀表达式：-×+3456

**前缀表达式的计算机求值：**
从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出顶的两个数，用运算符对它们做相应的计算(栈顶元素 和 次顶元素)，并将结果入栈;重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果

例如: (3+4)x5-6 对应的前缀表达式就是-x+3456,针对前缀表达式求值步骤如下:

1. 从右至左扫描，将6、5、4、3压入堆栈
2. 遇到+运算符，因此弹出3和4(3为栈顶元素，4为次顶元素)，计算出3+4的值，得7再将7入栈
3. 接下来是x运算符，因此弹出7和5，计算出7x5=35，将35入栈
4. 最后是-运算符，计算出35-6的值，即29，由此得出最终结果

**中缀表达式：**

中缀表达式就是常见的运算表达式，如(3+4)x5-6

中缀表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作(前面我们讲的案例就能看的这个问题)，因此，在计算结果时，往往会将中缀表达式转成其它表达式来操作一般转成后缀表达式.)

**后缀表达式：**

后缀表达式又称逆波兰表达式，与前缀表达式相似，指示运算符位于操作数之后

**后缀表达式的计算机求值：**

从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出顶的两个数，用运算符对它们做相应的计算(次顶元素 和栈顶元素)，并将结果入栈;重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果

例如:(3+4)x5-6 对应的前缀表达式就是34+5 x6-,针对后缀表达式求值步骤如下:

1. 从左至右扫描，将3和4压入堆栈;
2. 遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算出3+4的值，得7，再将7入栈;
3. 将5入栈;
4. 接下来是x运算符，因此弹出5和7，计算出7x5=35，将35入栈:
5. 将6入栈;
6. 最后是-运算符，计算出35-6的值，即29，由此得出最终结果

**中缀表达式转化后缀表达式：**

1. 初始化两个栈:运算符栈s1和储存中间结果的栈s2;
2. 从左至右扫描中缀表达式;
3. 遇到操作数时，将其压s2;
4. 遇到运算符时，比较其与s1栈顶运算符的优先级:
   1. 如果s1为空，或栈顶运算符为左括号“”，则直接将此运算符入栈;
   2. 否则，若优先级比核顶运算符的高，也将运算符压入s1;
   3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较;
5. 遇到括号时:
   1. 如果是左括号“(”，则直接压入s1
   2. 如果是右括号“)"，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
6. 重复步骤2至5，直到表达式的最右边
7. 将s1中剩余的运算符依次弹出并压入s2
8. 将s1中剩余的运算符依次弹出并压入s2

**代码实现：**

```java
package com.wwy.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    /**
     * 逆波兰表达式四则运算
     *
     * @param args
     */
    public static void main(String[] args) {
        String exp = "(3-1)*5";
        //将中缀表达式按字符存入list
        List<String> infixExpressionList = toInfixExpressionList(exp);
        //将中缀表达式转为逆波兰表达式
        List<String> strings = listParseSuffixExpressionList(infixExpressionList);
        System.out.println(strings);
        System.out.println(calCulate(strings));
    }

    /**
     * 将中缀表达式list转为逆波兰表达式
     */
    public static List<String> listParseSuffixExpressionList(List<String> str) {
        //初始化运算符s1栈
        Stack<String> s1 = new Stack<>();
        //初始化存储结果的s2栈，因为s2全程没有pop,所以就用list代替
        ArrayList<String> s2 = new ArrayList<>();
        for (String s : str) {
            //如果遇到操作数时，将其压s2;
            if (s.matches("\\d+")) {
                s2.add(s);
            } else {
                //如果s1为空，或栈顶运算符为左括号“”，则直接将此运算符入栈;
                if (s1.isEmpty() || s.equals("(")) {
                    s1.push(s);
                    //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                } else if (s.equals(")")) {
                    while (!s1.peek().equals("(")) {
                        s2.add(s1.pop());
                    }
                    s1.pop();
                } else {
                    //栈顶运算符的优先级高于当前字符，加入s2
                    while (s1.isEmpty() && operPriority(s1.peek()) > operPriority(s)) {
                        s2.add(s1.pop());
                    }
                    //否则加入s1
                    s1.push(s);
                }
            }
        }
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将中缀表达式按字符存入list
     *
     * @param exp
     * @return
     */
    public static List<String> toInfixExpressionList(String exp) {
        //定义一个list存放中缀表达式对应的内容
        ArrayList<String> list = new ArrayList<>();
        int i = 0;//指针
        String str;//多位数拼接
        char c;//没遍历到一个字符，就存入
        do {
//            如果是一个非数字，加入到list
            if ((exp.charAt(i) < 48) || (exp.charAt(i) > 57)) {
                list.add(String.valueOf(exp.charAt(i)));
                i++;
            } else {
                //将str设为空
                str = "";
//                如果是数字，就要考虑多位数
                while (i < exp.length() && (exp.charAt(i)) >= 48 && (exp.charAt(i)) <= 57) {
                    str += exp.charAt(i);
                    i++;
                }
                list.add(str);
            }

        } while (i < exp.length());

        return list;
    }

    /**
     * 将逆波兰表达式存入list集合中
     *
     * @param exp
     * @return
     */
    private static List<String> getListString(String exp) {
        String[] exps = exp.split(" ");
        ArrayList<String> stringList = new ArrayList<>();
        for (String s : exps) {
            stringList.add(s);
        }
        return stringList;
    }

    /**
     * 计算逆波兰表达式的结果
     *
     * @param list
     * @return
     */
    private static int calCulate(List<String> list) {
        Stack<String> stringStack = new Stack<>();
        for (String l : list) {
            if (l.matches("\\d+")) {
                stringStack.push(l);
            } else {
                int num1 = Integer.parseInt(stringStack.pop());
                int num2 = Integer.parseInt(stringStack.pop());
                int res = 0;
                if (l.equals("+")) {
                    res = num2 + num1;
                } else if (l.equals("-")) {
                    res = num2 - num1;
                } else if (l.equals("*")) {
                    res = num2 * num1;
                } else if (l.equals("/")) {
                    res = num2 / num1;
                }
                stringStack.push(String.valueOf(res));

            }
        }
        return Integer.parseInt(stringStack.pop());
    }

    /**
     * 判断当前运算符的优先级
     *
     * @param oper
     * @return
     */
    public static int operPriority(String oper) {
        if (oper.equals("+") || oper.equals("-")) {
            return 0;
        } else if (oper.equals("*") || oper.equals("/")) {
            return 1;
        }
        return -1;
    }
}
```

## 递归---Recursion

### 1.递归的概念

递归算法是一种直接或者间接调用自身方法的算法。简言之：**在定义自身的同时又出现自身的直接或间接调用。**

>注意：
>
>**递归必须要有一个退出的条件!**

递归算法解决问题的特点：
1）递归就是方法里调用自身。
2）在使用递增归策略时，必须有一个明确的递归结束条件，称为递归出口。
3）递归算法解题通常显得很简洁，但递归算法解题的运行效率较低。所以一般不提倡用递归算法设计程序。
4）在递归调用的过程当中系统为每一层的返回点、局部量等开辟了栈来存储。递归次数过多容易造成栈溢出等，所以一般不提倡用递归算法设计程序。
![image-20250123143000881](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123143000881.png)

>注意：
>
>在做递归算法的时候，一定要把握住**出口**，也就是做递归算法必须要有一个明确的递归结束条件。这一点是非常重要的。
>
>其实这个出口是非常好理解的，就是一个条件，当满足了这个条件的时候我们就不再递归了。

### 2.递归的应用

#### **范围内打印和n的阶乘**

**代码实现**

~~~java
package com.wwy.recursion;

public class RecursionTest {
    public static void main(String[] args) {
//        print(4);
factirial(4);
    }
//范围内打印
    public static void print(int n) {
        if (n > 2) {
            print(n - 1);
        } else {
            System.out.println("*-*");
        }
        System.out.print(n);
    }
//n的阶乘
    public static int factirial(int n) {
        if (n == 1) {
            return 1;
        } else {
            int res = factirial(n - 1) * n;
            System.out.println(res);
            return res;
        }
    }
}
~~~

#### 迷宫问题

利用递归和回溯实现迷宫寻找出口

**代码实现：**

~~~java
package com.wwy.recursion;

public class MiGong {
    public static void main(String[] args) {
        /**
         * 迷宫问题
         */
//        创建一个地图
        int[][] map = new int[8][7];
//   设置上下地图的墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
//        设置地图的左右墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        map[3][4] = 1;
        map[3][5] = 1;
        for (int[] m : map) {
            for (int d : m) {
                System.out.format("%3d", d);
            }
            System.out.println(" ");
        }
        setWay(map, 1, 1);
        System.out.println("-------------------------------------------------");
        for (int[] m : map) {
            for (int d : m) {
                System.out.format("%3d", d);
            }
            System.out.println(" ");
        }
    }

    /**
     * @param map 地图
     * @param i   出生点
     * @param j   出生点
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}

~~~

#### 八皇后问题

在8*8的棋盘上摆放八个皇后，使其不能相互攻击，任意两个皇后不能处于同一行，同一列或者同一斜线上，问有多少种摆法（92）



**算法思路分析：**

1. 第一个皇后先放第一行第一列
2. 第二个皇后放在第二行第一列、然后判断是否OK，如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
3. 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
4. 当得到一个正确解时，在栈回退到上一个栈时，就会开始回，即将第一个皇后，放到第一列的所有正确解，全部得到.
5. 然后回头继续第一个皇后放第二列，后面继续循环执行1,2,3,4的步骤

>**说明：**
>
>理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法用一个一维数组即可解决问题,arr[8]=(0,4,7,5,2,6,1,3}/对应arr 下标 表示第几行，即第几个皇后，arr[i]=val，val表示第i+1个皇后，放在第i+1行的第val+1列

**代码实现：**

```java
package com.wwy.recursion;

import java.util.List;

public class Queue8 {
    int maxSize = 8;
    int[] arr = new int[maxSize];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }

    /**
     * 放置第n个皇后
     *
     * @param n 表示当前放置第n行第n个皇后
     */
    public void check(int n) {
        if (n == maxSize) {
            printQueue();
            return;
        } else {
            //依次放入皇后，判断是否冲突
            for (int i = 0; i < maxSize; i++) {
                //先把这个皇后放置到第n个皇后到i列
                arr[n] = i;
                //判断是否冲突，如果不冲突，继续放下一个
                if (flag(n)) {
                    check(n + 1);
                }
            }
        }

    }

    /**
     * 判断要放置的棋子的前一个棋子是否冲突
     * 说明：
     * 1.arr[i] == arr[n]判断该第n个皇后和前一个n-1个皇后是否在同一列
     * 2.Math.abs(n - i) == Math.abs(arr[i] - arr[n])斜率相等，说明前后两个皇后在同一条直线上
     * 3.没必要判断是否在同一行
     *
     * @param n 表示第n行第n个皇后
     */
    public boolean flag(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    public void printQueue() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("第" +Integer.parseInt(String.valueOf(i+ 1)) + "行" + "第" + Integer.parseInt(String.valueOf(arr[i] + 1))+ "列");
            System.out.print(",");
        }
        System.out.println(" ");
    }
}
```

