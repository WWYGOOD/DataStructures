package com.wwy.sparsearray;

public class Test {
    public static void main(String[] args) {
        System.out.println("-------原始数组-------------------------------");

        int[][] cheese = new int[11][11];
        cheese[0][3] = 1;
        cheese[2][5] = 2;
        cheese[4][1] = 1;
        listArr(cheese);
        System.out.println("-------原始数组转为稀疏数组-------------------------------");
        int num = 0;
        for (int i = 0; i < cheese.length; i++) {
            for (int j = 0; j < cheese[0].length; j++) {
                if (cheese[i][j] != 0) {
                    num++;
                }
            }
        }
        int[][] sparseArray = new int[num + 1][3];
        sparseArray[0][0] = cheese.length;
        sparseArray[0][1] = cheese[0].length;
        sparseArray[0][2] = num;
        int sparseCount = 1;
        for (int i = 0; i < cheese.length; i++) {
            for (int j = 0; j < cheese[0].length; j++) {
                if (cheese[i][j] != 0) {
                    sparseArray[sparseCount][0] = i;
                    sparseArray[sparseCount][1] = j;
                    sparseArray[sparseCount][2] = cheese[i][j];
                    sparseCount++;
                }
            }
        }
        listArr(sparseArray);
        System.out.println("-------稀疏数组转为原始数组-------------------------------");
        int row = sparseArray[0][0];
        int colum = sparseArray[0][1];
        int[][] cheese1 = new int[row][colum];
        for (int i = 1; i < sparseArray.length; i++) {
            int sparseRow = sparseArray[i][0];
            int sparseColum = sparseArray[i][1];
            int value = sparseArray[i][2];
            cheese1[sparseRow][sparseColum] = value;
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
