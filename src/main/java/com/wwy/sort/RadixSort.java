package com.wwy.sort;

import java.util.Arrays;
import java.util.OptionalInt;

public class RadixSort {
    public static void main(String[] args) {
        /**
         * 桶排序
         */
        int[] arr = {546, 412, 15, 7489, 321, 87486, 46, 134, 463};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //得到数组最大的位数
        int max = Arrays.stream(arr).max().getAsInt();
        int maxLength = (max + "").length();

        /*
        定义一个二维数组，表示10个桶，每一个桶就是一个一维数组
        说明：
            1.二维数组包含10个一维数组
            2.为了放置在放入数时出现溢出，每一个一维数组（桶）大小定为arr.length
         */
        int[][] buckets = new int[10][arr.length];
        //记录的是某一个桶中的元素个数，根据这个来遍历，bucketElementsCount[0]就是buckets[0][?]
        int[] bucketElementsCount = new int[10];
        //针对每个元素的每位进行排序，从个位开始，长度为最大数的位数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //将每一个元素的每一位遍历出来，存入桶中
            for (int j = 0; j < arr.length; j++) {
                int digitElement = arr[j] / n % 10;//从个位开始
                //将当前元素存入对应的桶中
                buckets[digitElement][bucketElementsCount[digitElement]] = arr[j];
                //将桶内元素数量++
                bucketElementsCount[digitElement]++;
            }
            int index = 0;//原始数组的下标
            //遍历桶s，按顺序取出元素
            for (int k = 0; k < buckets.length; k++) {
                //如果桶中有元素，就按顺序取出元素
                if (bucketElementsCount[k] != 0) {
                    //遍历桶内的元素
                    for (int l = 0; l < bucketElementsCount[k]; l++) {
                        arr[index] = buckets[k][l];
                        index++;
                    }
                }
                //将桶取空，要将桶元素的数量清空
                bucketElementsCount[k] = 0;
            }
        }

    }
}
