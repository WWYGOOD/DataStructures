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
