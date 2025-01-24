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

### 1.稀疏数组---SparseArray

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

### 2.队列---ArrayQueue

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

### 1.单向链表---LinkedList

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

### 2.双向链表---DoubleLinkedList

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

### 3.循环链表---CricleLinkedList

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

#### 2.1**范围内打印和n的阶乘**

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

#### 2.2迷宫问题

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

#### 2.3八皇后问题

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

## 排序算法---SortAlgorithm

### 1.排序算法的介绍

​	排序也称排序算法，排序是将一组数据，依照指定的顺序进行排列的过程

### 2.排序的分类

- 内部排序
    - 指将需要处理的所有数据都加载到内部存储器(内存)中进行排序
- 外部排序
    - 数据量过大，无法全部加载到内存中，需要借助外部存储器(外存)进行排序

**常见的排序算法如下：**

**1. 基于比较的排序算法**

这些算法通过比较元素的大小来决定它们的顺序。

**1.1 简单排序算法**

- **冒泡排序（Bubble Sort）**
    - 时间复杂度：O(n²)
    - 空间复杂度：O(1)
    - 特点：简单但效率低，适合教学用途。
- **选择排序（Selection Sort）**
    - 时间复杂度：O(n²)
    - 空间复杂度：O(1)
    - 特点：每次选择最小元素放到前面，不稳定。
- **插入排序（Insertion Sort）**
    - 时间复杂度：O(n²)
    - 空间复杂度：O(1)
    - 特点：对小规模数据或基本有序的数据效率高。

**1.2 高效排序算法**

- **快速排序（Quick Sort）**
    - 时间复杂度：平均 O(n log n)，最坏 O(n²)
    - 空间复杂度：O(log n)（递归栈）
    - 特点：分治法，性能优秀，但不稳定。
- **归并排序（Merge Sort）**
    - 时间复杂度：O(n log n)
    - 空间复杂度：O(n)
    - 特点：分治法，稳定，适合外部排序。
- **堆排序（Heap Sort）**
    - 时间复杂度：O(n log n)
    - 空间复杂度：O(1)
    - 特点：利用堆数据结构，不稳定。
- **希尔排序（Shell Sort）**
    - 时间复杂度：O(n log² n)（取决于间隔序列）
    - 空间复杂度：O(1)
    - 特点：插入排序的改进版，不稳定。

------

**2. 非基于比较的排序算法**

这些算法不通过比较元素的大小来决定顺序，而是利用数据的特定属性（如整数范围）。

**2.1 线性时间复杂度排序**

- **计数排序（Counting Sort）**
    - 时间复杂度：O(n + k)（k 是数据范围）
    - 空间复杂度：O(k)
    - 特点：适合数据范围较小的整数排序。
- **基数排序（Radix Sort）**
    - 时间复杂度：O(n * k)（k 是数字位数）
    - 空间复杂度：O(n + k)
    - 特点：适合整数或字符串排序。
- **桶排序（Bucket Sort）**
    - 时间复杂度：O(n + k)（k 是桶的数量）
    - 空间复杂度：O(n + k)
    - 特点：适合数据分布均匀的场景。

------

**3. 其他排序算法**

- **TimSort**
    - 时间复杂度：O(n log n)
    - 空间复杂度：O(n)
    - 特点：结合了归并排序和插入排序，Python 和 Java 的内置排序算法。
- **梳排序（Comb Sort）**
    - 时间复杂度：O(n log n)
    - 空间复杂度：O(1)
    - 特点：冒泡排序的改进版，通过动态间隔减少比较次数。
- **鸽巢排序（Pigeonhole Sort）**
    - 时间复杂度：O(n + k)（k 是数据范围）
    - 空间复杂度：O(k)
    - 特点：适合数据范围较小且密集的场景。
- **BogoSort**
    - 时间复杂度：平均 O(n * n!)，最坏无限
    - 空间复杂度：O(1)
    - 特点：随机排序，效率极低，仅用于教学或娱乐。

### 3.算法的时间复杂度

#### 3.1度量一个程序执行时间的两种方法

1. **事后统计的方法**

   这种方法可行,但是有两个问题:一是要想对设计的算法的运行性能进行评测，需要实际运行该程序;二是所得时间的统计量依赖于计算机的硬件、软件等环境因素,这种方式，要在同一台计算机的相同状态下运行，才能比较那个算法速度更快。

2. **事前估算的方法**

   通过分析某个算法的时间复杂度来判断哪个算法更优

#### 3.2时间频度

**基本介绍：**

时间频度:一个算法花费的时间与算法中语句的执行次数成正比例，哪个算法中语句执行次数多，它花费时间就多。**一个算法中的语句执行次数称为语句频度或时间频度**。记为T(n)。

![image-20250123181222181](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123181222181.png)

举例说明：忽略常数项

![image-20250123181332629](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123181332629.png)

>- 结论:
   >  - 2n+20 和 2n 随着n变大，执行曲线无限接近,20 可以忽略
>  - 3n+10 和 3n 随着n变大，执行曲线无限接近,10 可以忽略

举例说明：忽略低次项

![image-20250123181604380](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123181604380.png)

>- 结论:
   >  - 2n+20 和 2n 随着n 变大，执行曲线无限接近,20 可以忽略
>  - 3n+10 和 3n 随着n变大，执行曲线无限接近,10 可以忽略

举例说明：忽略系数

![image-20250123181920112](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123181920112.png)

>- 结论：
   >  - 随着n值变大，5n^2+7n 和 3n^2+2n，执行曲线重合,说明 这种情况下,5和3可以忽略
>  - 而n^3+5n和 6n^3+4n ，执行曲线分离，说明多少次方式关键

#### 3.3时间复杂度

1. 般情况下，算法中的基本操作语句的重复执行次数是问题规模n的某个函数，用 T(n)表示，若有某个辅助函数f(n),使得当n趋近于无穷大时,T(n)/f(n)的极限值为不等于零的常数,则称“(n)是 T(n)的同数量级函数。记作 T(n)O(f(n))，称0(f(n))为算法的渐进时间复杂度，简称时间复杂度。
2. T(n)不同,但时间复杂度可能相同。 如:T(n)=㎡ +7n+6 与 T(n)=3n'+2n+2 它们的 T(n)不同,但时间复杂度相同,都为 O(㎡)。
   计算时间复杂度的方法



**计算时间复杂度的方法:**

1. 用常数1代替运行时间中的所有加法常数T(n)=㎡+7n+6 =>T(n)=㎡ +7n+1
2. 修改后的运行次数函数中，只保留最高阶项T(n)=㎡+7n+1=>T(n)=n
3. 去除最高阶项的系数 T(n)=㎡ =>T(n)=n => O(㎡)

#### 3.4常见的时间复杂度

**常见的时间复杂度量级**

常数阶O(1)
对数阶O(log2N)
线性阶O(n)
线性对数阶O(nlog2N)
平方阶O(n^2)
立方阶O(n^3)
K次方阶O(n^k)
指数阶(2^n)

**常见的时间复杂度对应的图**：![image-20250123183305188](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123183305188.png)

1. 常见的算法时间复杂度由小到大依次为:0(1)<0(log2n)<0()<0(nlog2n)<0 (n2)<0(n3)< 0(nk)<O(2n)，随着问题规模n的不断增大，上述时间复杂度不断增大，算法的执行效率越低
2. 从图中可见，我们应该尽可能避免使用指数阶的算法

**常数阶O(1)**

~~~java
int i = 1;
int j = 2;
++i;
j++;
int m = i + j;
~~~

> 上述代码在执行的时候，它消耗的时候并不随着某个变量的增长而增长，那么无论这类代码有多长，即使有几十万行，都可以用O(1)来表示它的时间复杂度。

**线性阶O(n)**

~~~java
for(i=1; i<=n; ++i)
{
   j = i;
   j++;
}
~~~

>这段代码，for循环里面的代码会执行n遍，因此它消耗的时间是随着n的变化而变化的，因此这类代码都可以用O(n)来表示它的时间复杂度。

**对数阶O(log2N)**

线性对数阶O(nlogN) 其实非常容易理解，将时间复杂度为O(logn)的代码循环N遍的话，那么它的时间复杂度就是 n * O(logN)，也就是了O(nlogN)。

~~~java
int i = 1;
while(i<n)
{
    i = i * 2;
}
~~~

>从上面代码可以看到，在while循环里面，每次都将 i 乘以 2，乘完之后，i 距离 n 就越来越近了。我们试着求解一下，假设循环x次之后，i 就大于 2 了，此时这个循环就退出了，也就是说 2 的 x 次方等于 n，那么 x = log2^n
>也就是说当循环 log2^n 次以后，这个代码就结束了。因此这个代码的时间复杂度为：**O(log2n)**

**线性对数阶O(nlog2N)**

线性对数阶O(nlog2N) 其实非常容易理解，将时间复杂度为O(log2n)的代码循环N遍的话，那么它的时间复杂度就是 n * O(log2N)，也就是了O(nlog2N)。

~~~java
for(m=1; m<n; m++)
{
    i = 1;
    while(i<n)
    {
        i = i * 2;
    }
}
~~~

**平方阶O(n2)**

平方阶O(n²) 就更容易理解了，如果把 O(n) 的代码再嵌套循环一遍，它的时间复杂度就是 O(n²) 了。

~~~java
for(x=1; i<=n; x++)
{
   for(i=1; i<=n; i++)
    {
       j = i;
       j++;
    }
}
~~~

**立方阶O(n³)、K次方阶O(n^k)**

参考上面的O(n²) 去理解就好了，O(n³)相当于三层n循环，其它的类似。

除此之外，其实还有 、均摊时间复杂度、最坏时间复杂度、最好时间复杂度 的分析方法，有点复杂，这里就不展开了。

3.5平均时间复杂度和最坏时间复杂度

1. 平均时间复杂度是指所有可能的输入实例均以等概率出现的情况下该算法的运行时间。
2. 最坏情况下的时间复杂度称最坏时间复杂度。一般讨论的时间复杂度均是最坏情况下的时间复杂度。这样做的原因是:最坏情况下的时间复杂度是算法在任何输入实例上运行时间的界限，这就保证了算法的运行时间不会比最坏情况更长。
3. 平均时间复杂度和最坏时间复杂度是否一致，和算法有关

| 排序算法     | 平均时间复杂度 | 最好情况   | 最坏情况   | 空间复杂度 | 稳定性 | 适用场景                             |
| :----------- | :------------- | :--------- | :--------- | :--------- | :----- | :----------------------------------- |
| **快速排序** | O(n log n)     | O(n log n) | O(n²)      | O(log n)   | 不稳定 | 通用排序，性能优秀，适合大规模数据   |
| **归并排序** | O(n log n)     | O(n log n) | O(n log n) | O(n)       | 稳定   | 需要稳定排序或外部排序（如文件排序） |
| **堆排序**   | O(n log n)     | O(n log n) | O(n log n) | O(1)       | 不稳定 | 适合内存受限的场景                   |
| **希尔排序** | O(n log² n)    | O(n log n) | O(n²)      | O(1)       | 不稳定 | 中等规模数据，比插入排序更快         |
| **插入排序** | O(n²)          | O(n)       | O(n²)      | O(1)       | 稳定   | 小规模数据或基本有序的数据           |
| **冒泡排序** | O(n²)          | O(n)       | O(n²)      | O(1)       | 稳定   | 教学用途，实际应用较少               |
| **选择排序** | O(n²)          | O(n²)      | O(n²)      | O(1)       | 不稳定 | 教学用途，实际应用较少               |

### 4.算法的空间复杂度简介

#### 4.1基本介绍

- 类似于时间复杂度的讨论，一个算法的空间复杂度(Space Complexity)定义为该算法所耗费的存储空间，它也是问题规模n的函数。
- 空间复杂度(Space Complexity)是对一个算法在运行过程中临时占用存储空间大小的量度。有的算法需要占用的临时工作单元数与解决问题的规模n有关，它随着n的增大而增大，当n较大时，将占用较多的存储单元，例如快速排序和归并排序算法就属于这种情况
- 在做算法分析时，主要讨论的是时间复杂度。从用户使用体验上看，更看重的程序执行的速度。一些缓存产品(redis.memcache)和算法(基数排序)本质就是用空间换时间.

### 5. 冒泡排序---Bubble Sort

#### 5.1 基本介绍

冒泡排序(Bubble Soring)的基本思想是:通过对待排序序列从前向后(从下标较小的元素开始),依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。

因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此要在排序过程中设置一个标志 fag判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排1序写好后，在进行)

![image-20250123212700993](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250123212700993.png)

**代码实现：**

~~~java
package com.wwy.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        /**
         * 冒泡排序
         */
//        int[] arr = {3, -1, 5, -2, 8, 0};
        long start = System.currentTimeMillis();
        int[] arr = randomNum(80000);
        int[] sort = bubbleSort(arr);

        System.out.println(Arrays.toString(sort));
        System.out.println(System.currentTimeMillis()-start);
    }

    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }

    private static int[] bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        return arr;
    }
}

~~~

### 6.选择排序---Select Sort

#### 6.1 基本介绍

选择式排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出某一元素，再依规定交换位置后达到排序的目的。

**选择排序思想:**

选择排序(select sorting)也是一种简单的排序方法。它的基本思想是:第-次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…，第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。

**选择排序思路分析图：**

![image-20250124150234729](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124150234729.png)

**选择排序思路图解：**

![image-20250124150332842](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124150332842.png)

**代码实现：**

~~~java
package com.wwy.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 6,-1};
        int[] sort = selectSort(arr);
        System.out.println(Arrays.toString(sort));

        
    }
    private static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;//假定最小值为第一个下标为0，后面依次加1
            int min = arr[minIndex];//最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }
    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }
}

~~~

### 7.插入排序---Insert Sort

#### 7.1 基本介绍

插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的。插入排序法思想:

插入排序(Insertion Sorting)的基本思想是:把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有 n-1个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。

![image-20250124150849638](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124150849638.png)

**代码实现：**

~~~java
package com.wwy.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {5, 11, 3, 9};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        int[] arr = randomNum(100000);
        insertSort(arr);

        System.out.println(Arrays.toString(arr));
        System.out.println(System.currentTimeMillis()-start);
    }

    /**
     * 插入排序
     * @param arr 排序的数组
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //设置插入的值
            int insertVal = arr[i];
            //设置要插入的位置
            int insertIndex = i-1;
            /*
            给insertVal找到插入的位置
            说明：
            1.insertIndex >= 0保证找到插入位置，数组不越界
            2.arr[insertIndex] > insertVal，判断插入值前面位次的值是否大于插入值
                如果大于，就将当前位次的值替换到插入位置，再将位次值后移
             */
            while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出循环，说明找到了插入位置，insertIndex + 1
            if(insertIndex+1!=i){
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }
}

~~~

#### 7.2希尔排序交换法和移位法

希尔排序（Shell Sort）是一种改进的插入排序算法。它通过将待排序的数组元素按一定间隔分组，对每组进行插入排序，随着间隔逐渐减小，最终完成排序。

**核心思想**

希尔排序通过逐步减小间隔，使得数组逐渐趋于有序，最终实现整体排序。

**步骤**

1. **选择间隔序列**：确定一个间隔序列，通常初始间隔为数组长度的一半，之后逐步减半。
2. **分组排序**：按当前间隔将数组分组，对每组进行插入排序。
3. **减小间隔**：重复上述步骤，直到间隔为1，完成最后一次插入排序。

**示例**

假设数组为 `[8, 3, 5, 1, 4, 7, 6, 2]`，初始间隔为4：

1. 分组为 `[8, 4]`, `[3, 7]`, `[5, 6]`, `[1, 2]`，排序后为 `[4, 3, 5, 1, 8, 7, 6, 2]`。
2. 间隔减半为2，分组为 `[4, 5, 8, 6]`, `[3, 1, 7, 2]`，排序后为 `[4, 1, 5, 2, 6, 3, 8, 7]`。
3. 间隔为1，进行最后一次插入排序，得到 `[1, 2, 3, 4, 5, 6, 7, 8]`。

**代码实现：**

~~~java
package com.wwy.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();


        int[] arr = randomNum(100000);
//        int[] arr={3,5,1,-1,9,6,4651,99,-5};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(System.currentTimeMillis() - start);

    }

    /**
     * 希尔排序交换法
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int temp = 0;
        //设置gap为步长，将数组分组，每次除以2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //对每一组进行排序
            for (int i = gap; i < arr.length; i++) {
                //比较一组数据的第一个和加上步长的第二个数据，如果大于就交换，小于就比较下一组
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序移位法
     */
    public static void shellSort2(int[] arr) {
        //设置分组以及步长
        for (int gap = arr.length; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j;
                // 对当前元素进行插入排序
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                // 将 temp 插入到正确的位置
                arr[j] = temp;
            }
        }
    }

    private static int[] randomNum(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        return arr;
    }
}
~~~

### 8. 快速排序---Quick Sort

#### 8.1基本介绍

快速排序算法简单地说就是找一个基准数，比基准数大的数就放在基准数右边，比基准数小的就放在基准数左边，然后分别对基准数左边的序列后右边的序列进行前面的步骤，直到分出来的序列长度为一。

而想要实现上面的步骤需要提取三个关键点，**基准数，队首索引，队尾索引**。为了方便，我们每次都可以以序列**最左边**的数作为基准数，然后队首索引和队尾索引一开始分别是序列开头位置和序列结束位置，我们可以拿队尾元素与基准数比较，比基准数大那对尾索引就向前移动，比基准数小就将这个元素插入到队首位置，之后队首索引向后移动，然后下一次比较就拿队首元素与基准数相比较了，直到找到比基准数大的数，将元素插入到队尾索引位置之后再将队尾元素向前移，一次类推，直到队首索引与队尾索引相等，就将基准数插入到队尾与队首索引相等的这个位置，这个序列内部的比较和插入就完成了，然后开始子序列的比较和插入

#### 8.2 实现方法

方法其实很简单：分别从初始序列“6 1 2 7 9 3 4 5 10 8”两端开始“探测”。先从**右**往**左**找一个小于6的数，再从**左**往**右**找一个大于6的数，然后交换他们。这里可以用两个变量l(left)和r(right)，分别指向序列最左边和最右边。我们为这两个变量起个好听的名字“哨兵l”和“哨兵r”。刚开始的时候让哨兵l指向序列的最左边（即l=1），arr[l]=6。让哨兵r指向序列的最右边（即r=9）arr[r]=8，指向数字。

![image-20250124224710639](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124224710639.png)

首先哨兵l开始出动。因为此处设置的基准数是最左边的数，所以需要让哨兵r先出动，这一点非常重要（请自己想一想为什么）。哨兵r一步一步地向左挪动（即r–-），直到找到一个小于6的数停下来。接下来哨兵l再一步一步向右挪动（即l++），直到找到一个数大于6的数停下来。最后哨兵l停在了数字5面前，哨兵r停在了数字7面前。

![image-20250124224632019](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124224632019.png)

![image-20250124224739925](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124224739925.png)

现在交换哨兵l和哨兵r所指向的元素的值。交换之后的序列如下：

> 6 1 2 **5** 9 3 4 **7** 10 8

![image-20250124224948501](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124224948501.png)

![image-20250124225006405](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124225006405.png)

到此，第一次交换结束。接下来开始哨兵r继续向左挪动（再友情提醒，每次必须是哨兵r先出发）。他发现了4（比基准数6要小，满足要求）之后停了下来。哨兵l也继续向右挪动的，他发现了9（比基准数6要大，满足要求）之后停了下来。此时再次进行交换，交换之后的序列如下

> 6 1 2 5 **4** 3 **9** 7 10 8

第二次交换结束，“探测”继续。哨兵r继续向左挪动，他发现了3（比基准数6要小，满足要求）之后又停了下来。哨兵继续向右移动，糟啦！此时哨兵l和哨兵r相遇了，哨兵l和哨兵r都走到3面前。说明此时“探测”结束。我们将基准数6和3进行交换。交换之后的序列如下：

> **3** 1 2 5 4 **6** 9 7 10 8

![image-20250124225234428](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124225234428.png)

![image-20250124225248885](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124225248885.png)

![image-20250124225302716](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124225302716.png)

到此第一轮“探测”真正结束。此时以基准数6为分界点，6左边的数都小于等于6，6右边的数都大于等于6。回顾一下刚才的过程，其实哨兵j的使命就是要找小于基准数的数，而哨兵i的使命就是要找大于基准数的数，直到i和j碰头为止。

OK，解释完毕。现在基准数6已经归位，它正好处在序列的第6位。此时我们已经将原来的序列，以6为分界点拆分成了两个序列，左边的序列是“3 1 2 5 4”，右边的序列是“9 7 10 8”。接下来还需要分别处理这两个序列。因为6左边和右边的序列目前都还是很混乱的。不过不要紧，我们已经掌握了方法，接下来只要模拟刚才的方法分别处理6左边和右边的序列即可。现在先来处理6左边的序列现吧。

左边的序列是“3 1 2 5 4”。请将这个序列以3为基准数进行调整，使得3左边的数都小于等于3，3右边的数都大于等于3。好了开始动笔吧

如果你模拟的没有错，调整完毕之后的序列的顺序应该是：

2 1 **3** 5 4

OK，现在3已经归位。接下来需要处理3左边的序列“2 1”和右边的序列“5 4”。对序列“2 1”以2为基准数进行调整，处理完毕之后的序列为“1 2”，到此2已经归位。序列“1”只有一个数，也不需要进行任何处理。至此我们对序列“2 1”已全部处理完毕，得到序列是“1 2”。序列“5 4”的处理也仿照此方法，最后得到的序列如下：

1 2 3 4 5 6 9 7 10 8

对于序列“9 7 10 8”也模拟刚才的过程，直到不可拆分出新的子序列为止。最终将会得到这样的序列，如下

1 2 3 4 5 6 7 8 9 10

到此，排序完全结束。细心的同学可能已经发现，快速排序的每一轮处理其实就是将这一轮的基准数归位，直到所有的数都归位为止，排序就结束了。下面上个霸气的图来描述下整个算法的处理过程。

![image-20250124225408960](https://web-wwy.oss-cn-beijing.aliyuncs.com/madkdown/image-20250124225408960.png)

这是为什么呢？

快速排序之所比较快，因为相比冒泡排序，每次交换是跳跃式的。每次排序的时候设置一个基准点，将小于等于基准点的数全部放到基准点的左边，将大于等于基准点的数全部放到基准点的右边。这样在每次交换的时候就不会像冒泡排序一样每次只能在相邻的数之间进行交换，交换的距离就大的多了。因此总的比较和交换次数就少了，速度自然就提高了。当然在最坏的情况下，仍可能是相邻的两个数进行了交换。因此快速排序的最差时间复杂度和冒泡排序是一样的都是O(N2)，它的平均时间复杂度为O(NlogN)。

##### 8.2.1快速排序（交换法）

~~~java
public class QuickSort {
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];
 
        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
         arr[low] = arr[i];
         arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }
 
    public static void main(String[] args){
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
~~~

##### 8.2.2 快速排序（填坑法）

~~~java
package com.wwy.sort;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, 7, 5, 1,-5494,-5454,222,112,1545,-44,11,1235,88,-48894,-1};
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        // 输出：1 3 5 6 8 9 
    }

    public static void sort(int[] arr, int left, int right) {
        if (right <= left) return;
        //选择最左侧数为基准数
        int pivot = arr[left];

        int begin = left, end = right;
        while (left < right) {
            // 从右向左找第一个小于pivot的元素
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right]; // 将找到的小于pivot的值填到左坑

            // 从左向右找第一个大于pivot的元素
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left]; // 将找到的大于pivot的值填到右坑
        }
        arr[left] = pivot; // 最后将基准值填入中间

        sort(arr, begin, left - 1);
        sort(arr, left + 1, end);
    }

}
~~~

