package com.wwy.sparsearray;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
